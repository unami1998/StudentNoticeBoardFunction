package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int age;
    private String name;
    private String address;

//    @ElementCollection
//    private lString[] grade = {"freshman","sophomore","junior","senior"};

    private String grade;
    private int pointCount;

    @OneToMany
    @JoinColumn(name="student")
    private List<Item> item;

    @OneToMany
    @JoinColumn(name="grade")
    private List<Grade> upgrade;

    public Student(){
        this.grade = "freshman"; //기본 값 이걸로 설정
    }
    public void increaseGrade(){
        pointCount++;
        System.out.println("학점 증가 현재학점" +pointCount);
    }

}
