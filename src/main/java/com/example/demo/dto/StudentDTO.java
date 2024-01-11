package com.example.demo.dto;

import com.example.demo.entity.Job;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly=true)
public class StudentDTO {
    @Autowired
    private StudentRepository studentRepository;

    //Student 추가
    @Transactional
    public Long join(Student student){
        validateDuplicateStudent(student); //중복학생 검증
        Job job = new Job(student.getGrade(), );
        studentRepository.save(student); //이렇게 해도 추가가 된다
        return student.getId();
    }

    private void validateDuplicateStudent(Student student) {
        List<Student> findByName = studentRepository.findByName(student.getName()); //이름으로 중복찾기
        if(!findByName.isEmpty()){ //중복된 이름 empty가 아니라면 이미 누군가 그 이름을 쓴다는거니깐
            throw new IllegalStateException("이미 존재하는 이름");
        }
    }

}
