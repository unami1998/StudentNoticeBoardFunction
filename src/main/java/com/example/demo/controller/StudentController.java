package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Job;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StudentController {
    private StudentDTO studentDTO;

    @PostMapping("/student/new")
    public String create(StudentDTO form, @RequestBody String name, @RequestBody int age, @RequestBody String address){
        Job job = new Job(form.);
        Student student = new Student();
        student.setName(name);

    }
}
