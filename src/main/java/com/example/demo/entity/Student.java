package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int age;
    private String name;

    @Embedded
    private Job job; //하나의 객체로 묶인다 사용하는 곳에서 씀

    @OneToMany
    @JoinColumn(name="student_pick_item")
    private List<Item> item;

    public Student(Long id, String name, int age, String address, Job job){
        this.id= id;
        this.name= name;
        this.age= age;
        this.job= job;
    }

    public Student() {

    }
}
