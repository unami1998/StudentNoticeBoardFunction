package com.example.demo.service;


import com.example.demo.controller.MailService;
import com.example.demo.dto.MyAccountInfoDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.changeInfoRequestDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    MailService mailService;

    public Long join(StudentDTO joinStudent){
        validateDuplicateStudent(joinStudent.getName());  //이름이 중복
        Student student = new Student();
        student.setName(joinStudent.getName());
        student.setEmail(joinStudent.getEmail());
        student.setNickName(joinStudent.getName());
        student.setPassword(joinStudent.getPassword());
        studentRepository.save(student); //이렇게 해도 추가가 된다
        return student.getId();
    }
    public void validateDuplicateStudent(String studentName) {
        List<Student> existStudent = studentRepository.findByName(studentName);  //이름으로 중복찾기
        if(!existStudent.isEmpty()){ //중복된 이름 empty가 아니라면 이미 누군가 그 이름을 쓴다는거니깐
            throw new DuplicateFormatFlagsException("이미 존재하는 이름");
        }
    }
    public MyAccountInfoDTO login(String email, String password) {
        Student student = studentRepository.findByEmailAndPassword(email,password);
        if(student ==null){
            return null;
        }
        if(!student.getPassword().equals(password)){
            return null;
        }
        MyAccountInfoDTO myAccountInfoDTO = new MyAccountInfoDTO();
        myAccountInfoDTO.setId(student.getId());
        myAccountInfoDTO.setNickName(student.getNickName());
        myAccountInfoDTO.setEmail(student.getEmail());

        return myAccountInfoDTO;
    }



    public long findPassword(String email) throws MessagingException, UnsupportedEncodingException {
        Student student = studentRepository.findByEmail(email);
        if(student == null || !student.getEmail().equals(email)) {
            return -1;
        }
        mailService.sendSimpleMessage(email);
        return 1;
    }

    public List<StudentDTO> getAllMembers() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Student student : students){
            StudentDTO dto = new StudentDTO();
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            studentDTOS.add(dto);
        }

        return studentDTOS;
    }

    public StudentDTO myInfo(MyAccountInfoDTO myAccountInfo) {
        if (myAccountInfo == null) {
            System.out.print("user 없음");
        }
        Long id = myAccountInfo.getId();
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){ //그 해당 아이디가 있다면
            Student student=optionalStudent.get();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(student.getName());
            studentDTO.setNickName(student.getNickName());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setAge(student.getAge());
            studentDTO.setGrade(String.valueOf(student.getGrade()));
            studentDTO.setPointCount(student.getPointCount());

            return studentDTO;
        } else {
            System.out.println("해당 id에 해당하는 Student가 존재하지 않습니다.");
            return null;
        }
    }

    public void changeMyInfo(changeInfoRequestDTO changeInfo) {
        Student student = studentRepository.findUserById(changeInfo.getId());
        if (student != null) {
            if (changeInfo.getName() != null) {
                student.setName(changeInfo.getName());
            }
            if (changeInfo.getNickName() != null) {
                student.setNickName(changeInfo.getNickName());
            }
            if (changeInfo.getAddress() != null) {
                student.setAddress(changeInfo.getAddress());
            }
            if (changeInfo.getAge() != null) {
                student.setAge(changeInfo.getAge());
            }
            studentRepository.save(student);
        }else{
            System.out.print("야 유저 없는데?");
        }
    }
}
