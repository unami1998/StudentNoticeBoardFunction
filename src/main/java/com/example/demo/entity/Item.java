package com.example.demo.entity;

import com.example.demo.dto.AddRequestItemDto;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemPrice;

    private String itemName;
    private String itemComment;

    @OneToMany
    @JoinColumn(name="native_student")
    List<School> mySchool;

    @OneToMany
    @JoinColumn(name="student_pick_item")
    private List<Student> students;

}
