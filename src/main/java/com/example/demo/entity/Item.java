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
    @JoinColumn(name="order")
    private Order order;


}
