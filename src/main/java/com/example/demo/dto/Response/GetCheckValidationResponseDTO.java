package com.example.demo.dto.Response;

import com.example.demo.dto.CheckNameContainsDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.fasterxml.classmate.MemberResolver;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class GetCheckValidationResponseDTO extends ResponseDTO.Result {
    @Setter
    private String isTest = "true";
    private StudentRepository studentRepository;
    private Student student;
    private GetCheckValidationResponseDTO(String name) {
        String testName = studentRepository.findByName(student.getName()).toString();
        if (!testName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
    }



}
