package com.example.demo.service;


import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public int join(StudentDTO joinStudent){
        validateDuplicateStudent(joinStudent.getName());  //이름이 중복
        Student student = new Student();
        student.setName(joinStudent.getName());
        student.setNickName(joinStudent.getNickName());
        student.setEmail(joinStudent.getEmail());
        student.setPassword(joinStudent.getPassword());
        studentRepository.save(student); //이렇게 해도 추가가 된다
        return student.getId();
    }

    public long login(String email, String password) {
        Student student = studentRepository.findByEmailAndPassword(email,password);
        if(!student.equals(email)){
            return -1;
        }
        if(!student.equals(password)){
            return -2;
        }
        return 1;
    }

    public void validateDuplicateStudent(String studentName) {
        List<Student> existStudent = studentRepository.findByName(studentName);  //이름으로 중복찾기
        if(!existStudent.isEmpty()){ //중복된 이름 empty가 아니라면 이미 누군가 그 이름을 쓴다는거니깐
            throw new DuplicateFormatFlagsException("이미 존재하는 이름");
        }
    }

    public List<Student> getAllMembers() {
        System.out.print("studentList:" + studentRepository.findAll());
        return studentRepository.findAll();
    }

    public StudentDTO myInfo(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){ //그 해당 아이디가 있다면
            Student student=optionalStudent.get();
            StudentDTO studentDTO = new StudentDTO();
            //studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
<<<<<<< HEAD
         //   studentDTO.setAge(student.getAge());
=======

>>>>>>> 597aae8 (//회원가입할 때 이름, 닉네임, 이메일, 비밀번호 입력하기)
            return studentDTO;
        } else {
            System.out.println("해당 id에 해당하는 Student가 존재하지 않습니다.");
            return null;
        }
    }


}
