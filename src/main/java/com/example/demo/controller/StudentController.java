package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/student")
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/list")   //모든 회원 리스트 뿌리기
    public List<Student> GetAllMember(){
        return studentService.getAllMembers();
    }
    @PostMapping("/login")
    public ResponseDTO createStudent(@RequestBody StudentDTO student) {
        studentService.join(student);
        System.out.println(student.getName());
        return ResponseDTO.test("작업 중입니다(studentId:" + student + ")");
    }
    @GetMapping("/grade")
    public ResponseDTO gradeStudent(@RequestParam Long studentId){
        studentService.pointStudent(studentId);
        return ResponseDTO.test("작업중입니다.");
    }


}
