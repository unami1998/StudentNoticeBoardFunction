package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.dto.Response.KakaoUserInfoResponse;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {
    /*
조회할 때 회원명 얼룩이 할 때

얼
  룩이
  곰이
  고라몽

이런 거 기술 여튼 이런식으로 하는거 구현


*/

    @Autowired
    private StudentService studentService;

    @GetMapping("/loginPage")
    public String loginForm() {
        return "index"; // login.html 템플릿 반환
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
        String loginResult = studentService.login(email, password);
        if (loginResult.equals(" ")) {
            System.out.println("로그인 실패");
            model.addAttribute("showModal", true);
            return "index"; // 로그인 페이지로 다시 돌아감
        }
//        if (loginResult == -2) {
//            System.out.println("비밀번호 틀림");
//            model.addAttribute("showModal", true);
//            return "index"; // 로그인 페이지로 다시 돌아감
//        }
        model.addAttribute("nick_name",loginResult);
        return "home"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }


    @PostMapping("/createStudent")
    public String createStudent(StudentDTO joinStudent,
                        Model model) {
        studentService.join(joinStudent);
        model.addAttribute("myName", joinStudent.getName());


        return "home"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }

    @GetMapping("/passwordFind")
    public String showPasswordFindForm() {
        return "passwordFind"; // 여기서 'passwordFind'는 HTML 파일 이름에 해당합니다
    }

    @PostMapping("/findPassword")
    public String findPassword(String email,Model model) throws MessagingException, UnsupportedEncodingException {
        //Email이 db에 없는 비밀번호면 오류
        long findPassword = studentService.findPassword(email);
        if(findPassword ==-1){
            model.addAttribute("findModal", true);
        }

        return "index"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }


}

