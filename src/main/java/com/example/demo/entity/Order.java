package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Student_id")
    private Student student;

    private LocalDateTime orderDate; //주문시간

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> orderItems = new ArrayList<>();

}
