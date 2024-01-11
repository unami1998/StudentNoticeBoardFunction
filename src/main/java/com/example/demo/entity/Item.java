package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String itemName;
    private int price;
    @ManyToOne
    @JoinColumn(name="native_student")
    private Student student;

    //==연관관계 메서드==//
    public void setStudent(Student student){
        this.student = student;

    }


}
