package com.example.demo.controller;

import com.example.demo.dto.CheckNameContainsDTO;
import com.example.demo.dto.Response.GetCheckValidationResponseDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Job;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/new")
    public ResponseDTO create(@RequestBody StudentDTO form){

        try {
            long studentId= studentService.join(form);
            System.out.println("반갑다");
            return ResponseDTO.test("작업 중입니다(studentId:"+studentId+")");
        }catch (Exception ex)
        {
            return GetCheckValidationResponseDTO.create(GetCheckValidationResponseDTO.class, false, ex.getMessage())
                    .toResponseDTO();
        }
    }

    @GetMapping("/checkName")   //중복 이름 점검
    public ResponseDTO checkNameContains(@RequestParam String name){

        try {
            studentService.validateDuplicateStudent(name);
        }catch (Exception ex){
            return GetCheckValidationResponseDTO.create(GetCheckValidationResponseDTO.class, false, ex.getMessage())
                    .toResponseDTO();
        }
        return GetCheckValidationResponseDTO.create(GetCheckValidationResponseDTO.class, true, "")
                .toResponseDTO();
    }
}
