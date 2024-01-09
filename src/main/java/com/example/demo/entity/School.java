package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //이렇게만 하면 primary key를 자동으로 해준다
    private int student_id;

    private String message;

    @ManyToOne
    @JoinColumn(name="school_student")
    private Student student;

}