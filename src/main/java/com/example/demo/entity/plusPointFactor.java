package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class plusPointFactor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String volunteer;
    private String grade;

}
