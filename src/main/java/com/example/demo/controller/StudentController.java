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

import java.util.List;


@RequestMapping("/student")
@Controller

public class StudentController {
    private KakaoTokenJsonData kakaoTokenJsonData;
    private KakaoUserInfo kakaoUserInfo;

    @Autowired
    private StudentService studentService;
    @GetMapping("/list")   //모든 회원 리스트 뿌리기
    public List<Student> GetAllMember(){
        return studentService.getAllMembers();
    }

    @PostMapping("/createStudent")
    public ResponseDTO createStudent(@RequestBody StudentDTO student) {
        studentService.join(student);
        return ResponseDTO.create(true,"회원가입성공").toResponseDTO();
    }

//    @GetMapping("/loginTakao")
//    public String loginForm(){
//        return "loginTakao";
//    }

//    @Description("회원이 소셜 로그인을 마치면 자동으로 실행되는 API입니다. 인가 코드를 이용해 토큰을 받고, 해당 토큰으로 사용자 정보를 조회합니다." +
//            "사용자 정보를 이용하여 서비스에 회원가입합니다.")
//    @GetMapping("/oauth")
//    @ResponseBody
//    public String kakaoOauth(@RequestParam("code") String code) {
//        log.info("인가 코드를 이용하여 토큰을 받습니다.");
//        KakaoTokenResponse kakaoTokenResponse = kakaoTokenJsonData.getToken(code);
//        log.info("토큰에 대한 정보입니다.{}",kakaoTokenResponse);
//        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(kakaoTokenResponse.getAccess_token());
//        log.info("회원 정보 입니다.{}",userInfo);
//
//        studentService.join2(userInfo.getKakao_account().getEmail());
//
//        return "okay";
//    }


    @GetMapping("/login")
    public String loginForm() {
        System.out.println("test");
        return "login";
    }

//    @PostMapping("/login")
//    public ResponseDTO loginStudent(@RequestBody StudentLoginDTO login) {
//        long defaultLogin = studentService.login(login.getEmail(), login.getPassword());
//        if (defaultLogin == -1){
//            return ResponseDTO.create(false,"이메일틀림").toResponseDTO();
//
//        }
//        if(defaultLogin == -2){
//            return ResponseDTO.create(false,"비밀번호틀림").toResponseDTO();
//
//        }
//
//        return ResponseDTO.create(true,"로그인성공").toResponseDTO();
//    }
    @GetMapping("/myinfo")
    public ResponseDTO gradeStudent(@RequestParam Long studentId){
        studentService.myInfo(studentId);
        return ResponseDTO.test("작업중입니다.");
    }

}
