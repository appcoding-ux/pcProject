package com.project.pc.controller;

import com.project.pc.dto.MemberDTO;
import com.project.pc.security.dto.MemberSecurityDTO;
import com.project.pc.service.member.MemberService;
import com.project.pc.service.payment.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Log4j2
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public void loginGET(String error, Model model){
        model.addAttribute("error", error);
    }

    // 아이디 중복 확인
    @GetMapping("/idCheck")
    public ResponseEntity<Boolean> joinIdCheck(String id) throws Exception {
        MemberDTO memberDTO = memberService.idCheck(id);

        if(memberDTO != null){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    // 회원가입 후 로그인 페이지 이동
    @PostMapping("/join")
    public String joinPOST(MemberDTO memberDTO, RedirectAttributes redirectAttributes){
        memberService.memberInsert(memberDTO);

        redirectAttributes.addFlashAttribute("result", "joinSuccess");

        return "redirect:/member/login";
    }

    // 관리자의 강제 회원 삭제
    @PostMapping("/forceRemove")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String forceRemove(String id, RedirectAttributes redirectAttributes){
        System.out.println(id);
        memberService.removeMember(id);

        redirectAttributes.addFlashAttribute("result", "removeMember");

        return "redirect:/admin/member";
    }

    @GetMapping("/findPass")
    public void getFindPass(){}

    @PostMapping("/findPass")
    public String postFindPass(String email, Model model){
        model.addAttribute("email", email);
        return "member/updatePass";
    }

    @PostMapping("/updatePass")
    public String updatePass(String email, String password, Model model){
        memberService.updatePass(email, password);

        model.addAttribute("result", "updatePass");

        return "/member/updatePass";
    }
}
