package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.dto.Response.KakaoUserInfoResponse;
import com.example.demo.entity.Board;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/changeMyInfo")
    public ResponseEntity<String> changeMyInfo(HttpSession session, @RequestBody changeInfoRequestDTO changeInfo) {
        MyAccountInfoDTO user = (MyAccountInfoDTO) session.getAttribute("currentUser");
        changeInfo.setId(user.getId());
        studentService.changeMyInfo(changeInfo);
        return ResponseEntity.ok("Info updated successfully");
    }

    @GetMapping("/myProfile")
    public String myprofileForm(HttpSession session, Model model) {
        //세션에서 현재 사용자 정보 가져오기
        MyAccountInfoDTO user = (MyAccountInfoDTO) session.getAttribute("currentUser");
        if (user == null || user.getId() ==0L) {
            model.addAttribute("error", "로그인 정보가 유효하지 않습니다.");
            System.out.print("여기서 망함?");
            return "home"; // 로그인 페이지로 리다이렉트
        }
        StudentDTO studentDTO = studentService.myInfo(user);
        model.addAttribute("student", studentDTO);
        return "myProfile";
    }

    @GetMapping("/allStudent")
    public String allStudent(Model model) {
        List<StudentDTO> students = studentService.getAllMembers();
        model.addAttribute("students", students);
        return "allStudent";
    }

    @GetMapping("/loginPage")
    public String loginForm() {
        return "index"; // login.html 템플릿 반환
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        RedirectAttributes redirectAttributes,
                        HttpSession session,
                        @RequestParam String password,
                        Model model) {
        MyAccountInfoDTO loginResult = studentService.login(email, password);
        if (loginResult == null || loginResult.getNickName().trim().isEmpty()) {
            System.out.println("로그인 실패");
            return "index"; // 로그인 페이지로 다시 돌아감
        }
        session.setAttribute("currentUser", loginResult);
        String nickName = loginResult.getNickName();
        redirectAttributes.addAttribute("nick_name", nickName); // URL 파라미터로 전달

        //model.addAttribute("nick_name",loginResult);
        return "redirect:/board/home"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }


    @PostMapping("/createStudent")
    public String createStudent(StudentDTO joinStudent,
                                HttpSession session,
                                Model model) {
        MyAccountInfoDTO newUser = studentService.join(joinStudent);
        session.setAttribute("currentUser", newUser);
        model.addAttribute("myName", joinStudent.getName());
        String nickName = joinStudent.getName();

        return "index"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }

    @GetMapping("/passwordFind")
    public String showPasswordFindForm() {
        return "passwordFind"; // 여기서 'passwordFind'는 HTML 파일 이름에 해당합니다
    }

    @PostMapping("/findPassword")
    public String findPassword(String email, Model model) throws MessagingException, UnsupportedEncodingException {
        //Email이 db에 없는 비밀번호면 오류
        long findPassword = studentService.findPassword(email);
        if (findPassword == -1) {
            model.addAttribute("findModal", true);
        }

        return "index"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }

}

