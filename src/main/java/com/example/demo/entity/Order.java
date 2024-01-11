package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {
    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;
    private int itemCount;
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @OneToMany(mappedBy = "order")
    private List<Item> item = new ArrayList<>();
}
