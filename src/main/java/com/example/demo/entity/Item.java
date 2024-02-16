package com.example.demo.entity;

import com.example.demo.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private int id;

    private String itemName;
    private int price;

    private int stockQuantity; //재고수량

    @ManyToOne
    @JoinColumn(name = "id")
    private Student student;

//    @ManyToOne
//    @JoinColumn(name = "order_item")
//    private Item item;

    public Item() {

    }


    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) throws Exception {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new Exception("need more stock");
        }
        this.stockQuantity = restStock;
    }
    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + itemName + "', price=" + price + '}';
    }

}
