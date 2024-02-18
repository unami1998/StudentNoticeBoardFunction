package com.example.demo.service;


import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public int join(StudentDTO joinStudent){
        validateDuplicateStudent(joinStudent);
        Student student = new Student();
        student.setName(student.getName());
        student.setAge(student.getAge());
        studentRepository.save(student); //이렇게 해도 추가가 된다
        return student.getId();
    }

    public void validateDuplicateStudent(StudentDTO student) {
        List<Student> existStudent = studentRepository.findByName(student.getName());  //이름으로 중복찾기
        if(!existStudent.isEmpty()){ //중복된 이름 empty가 아니라면 이미 누군가 그 이름을 쓴다는거니깐
            throw new DuplicateFormatFlagsException("이미 존재하는 이름");
        }
    }

    public List<Student> getAllMembers() {
        System.out.print("studentList:" + studentRepository.findAll());
        return studentRepository.findAll();
    }

    public void pointStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            optionalStudent.get().increaseGrade();
        } else {
            System.out.println("해당 id에 해당하는 Student가 존재하지 않습니다.");
        }
    }
}
