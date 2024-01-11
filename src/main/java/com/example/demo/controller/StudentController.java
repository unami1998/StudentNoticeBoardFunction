package com.example.demo.controller;

import com.example.demo.dto.CheckNameContainsDTO;
import com.example.demo.dto.Response.GetCheckValidationResponseDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Job;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping("/new")
    public ResponseDTO create(@RequestBody Student form){
        Student student=  new Student();
        student.setName(form.getName());
        student.setAge(form.getAge());
        Job job = new Job();
        job.setAddress(form.getJob().getAddress());
        studentRepository.save(student);
        System.out.println("반갑다");
        return ResponseDTO.test("작업 중입니다");
    }

    @GetMapping("/checkName")
    public ResponseDTO checkNameContains(@RequestParam String name){
        return ResponseDTO.Result.create(GetCheckValidationResponseDTO.class, true, "")
                .toResponseDTO();
    }
}
