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
    private Long id;
    private int age;
    private String name; //이름
    private String nickName;
    private String email;
    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Grade grade;
    private int pointCount;
    public Student() {
        this.grade = Grade.FRESHMAN;
        this.pointCount = 0;
    }
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "student")
    private List<Item> item;
    public void increaseGrade() {
        this.pointCount ++;

        if(this.pointCount ==3){
            this.grade = Grade.SOPHOMORE;
        }else if (this.pointCount == 20) {
            this.grade = Grade.JUNIOR;
        } else if (this.pointCount == 30) {
            this.grade = Grade.SENIOR;
        }
    }
}
