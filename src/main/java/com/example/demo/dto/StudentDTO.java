package com.example.demo.dto;

import com.example.demo.entity.Grade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    private long id;
    private int age;
    private String name; //이름
    private String nickName;
    private String email;
    private String password;
    private String address;
    private String grade;
    private int pointCount;
}
