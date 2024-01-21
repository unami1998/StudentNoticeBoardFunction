package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.dto.Response.GetCheckValidationResponseDTO;
import com.example.demo.dto.Response.orderDto;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public ResponseDTO studentList(Model model) {
        model.addAttribute("form", new StudentDTO());
        return ResponseDTO.of();
    }
    @PostMapping("/addStudent")
    public ResponseDTO createStudent(@RequestBody Student student) {
        student.setName(student.getName());
        student.setAge(student.getAge());
        student.setAddress(student.getAddress());
        studentService.join(student);
        System.out.println(student.getName());
        return ResponseDTO.test("작업 중입니다(studentId:" + student + ")");
    }

    @PostMapping("/Buy/item")
    public ResponseDTO ordrStudent(@RequestParam("name") String studentName,
                                   @RequestParam("itemName") String itemName){
        studentService.orderStudent(studentName, itemName);
        return ResponseDTO.test("작업 중입니다");

    }


}
