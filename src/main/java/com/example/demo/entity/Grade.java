package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String volunteer;
    private String grade;

}
