package com.project.pc.controller;

import com.project.pc.dto.MemberDTO;
import com.project.pc.dto.PaymentDTO;
import com.project.pc.security.CustomUserDetailService;
import com.project.pc.service.member.MemberService;
import com.project.pc.service.payment.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.IamportPaycoClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class PayController {

    private final MemberService memberService;

    private final PaymentService paymentService;

    private final CustomUserDetailService userDetailService;

    @ResponseBody
    @PostMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> importVerify(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException {
        IamportClient iamportClient = new IamportPaycoClient("3214123007006164", "JSPBF6yDYVU0kGIOG1Pua25XziNyWYbU0hLvC8ido1yjHxV4i0qTqP9eiVF0wpfM0we19oz0XHtnY5vL");

        return iamportClient.paymentByImpUid(imp_uid);
    }

    @ResponseBody
    @PostMapping("/addTime")
    public ResponseEntity<String> addTime(Long num, String id, HttpServletRequest request, HttpServletResponse response){
        PaymentDTO paymentDTO = paymentService.getPayment(num);

        memberService.addTime(paymentDTO.getPaymentHour(), id);

        UserDetails newPrincipal = userDetailService.loadUserByUsername(id);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        token.setDetails(SecurityContextHolder.getContext().getAuthentication().getDetails());

        SecurityContextHolder.getContext().setAuthentication(token);

        String time = memberService.findMember(id).getTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        return new ResponseEntity<>(time, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/updateTime")
    public ResponseEntity<String> updateTime(String id){
        memberService.updateTime(id);

        UserDetails newPrincipal = userDetailService.loadUserByUsername(id);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        token.setDetails(SecurityContextHolder.getContext().getAuthentication().getDetails());

        SecurityContextHolder.getContext().setAuthentication(token);

        String time = memberService.findMember(id).getTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        return new ResponseEntity<>(time, HttpStatus.OK);
    }
}
