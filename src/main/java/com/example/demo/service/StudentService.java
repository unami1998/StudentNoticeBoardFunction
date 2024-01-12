package com.example.demo.service;


import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Job;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    //Student 추가
    public Long join(StudentDTO studentDTO){
        Student studentEntity=  new Student();
        studentDTO.setName(studentEntity.getName());
        studentDTO.setAge(studentEntity.getAge());
        Job job = new Job();
        job.setAddress(studentEntity.getJob().getAddress());
        validateDuplicateStudent(studentEntity.getName()); //중복학생 검증
        studentRepository.save(studentEntity); //이렇게 해도 추가가 된다

        return studentEntity.getId();
    }

    public void validateDuplicateStudent(String name) {
        List<Student> findByName = studentRepository.findByName(name); //이름으로 중복찾기
        if(!findByName.isEmpty()){ //중복된 이름 empty가 아니라면 이미 누군가 그 이름을 쓴다는거니깐
            throw new IllegalStateException("이미 존재하는 이름");
        }
    }

}
