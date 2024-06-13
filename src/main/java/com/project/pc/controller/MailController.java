package com.project.pc.controller;

import com.project.pc.service.MailService;
import com.project.pc.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailservice;

    private final MemberService memberService;

    @ResponseBody
    @PostMapping( "/mail")
    public String mailSend(String email){
        boolean frag = memberService.emailCheck(email);

        if(frag){
            return "alreadyThere";
        }else {
            int number = mailservice.sendMail(email);

            String num = "" + number;

            return num;
        }

    }

    @ResponseBody
    @GetMapping("/mail/findPass")
    public int findPassMailSend(String email){
        return mailservice.findPassSend(email);
    }
}
