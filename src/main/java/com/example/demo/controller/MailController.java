package com.example.demo.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/findPassword")
    String mailConfirm(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        long code = mailService.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return "passwordFind";
    }

    @GetMapping("/passwordFind")
    public String showPasswordFindForm() {
        return "passwordFind"; // 여기서 'passwordFind'는 HTML 파일 이름에 해당합니다
    }

}
