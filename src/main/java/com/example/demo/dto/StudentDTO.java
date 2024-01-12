package com.example.demo.dto;

import com.example.demo.entity.Job;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Setter
public class StudentDTO {
    private int age;
    private String name;
}
