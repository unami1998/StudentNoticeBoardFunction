package com.example.demo.repository;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByName(String name);

    Student findById(StudentDTO id);

    Student findByEmailAndPassword(String email, String password);
}
