package com.example.demo.entity;

import com.example.demo.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Entity
public class Item extends ItemDto {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private int id;

    private String itemName;
    private int price;
    private int count;

    private int stockQuantity; //재고수량

    @ManyToOne
    @JoinColumn(name = "id")
    private Student student;

    public Item() {

    }


    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + itemName + "', price=" + price + '}';
    }

}
