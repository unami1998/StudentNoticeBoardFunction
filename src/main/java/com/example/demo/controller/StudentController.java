package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.dto.Response.KakaoUserInfoResponse;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/loginPage")
    public String loginForm() {
        return "login"; // login.html 템플릿 반환
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes) {
        long loginResult = studentService.login(email, password);
        if (loginResult == -1) {
            System.out.println("로그인 실패");
            redirectAttributes.addAttribute("loginError", true);
            return "testModal"; // 로그인 페이지로 다시 돌아감
        }
        if (loginResult == -2) {
            System.out.println("비밀번호 틀림");
            return "testModal"; // 로그인 페이지로 다시 돌아감
        }
        return "redirect:home"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }

    @RequestMapping( value ="/abc/xyz.html")
    public String getMyModal()
    {
        //Do something here and return modal

        return "testModal";
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // home.html 템플릿 반환
    }
}

