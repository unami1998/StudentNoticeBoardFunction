package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private int id;

    private String itemName;
    private int price;
    private int count;

    private int stockQuantity; //재고수량

    @ManyToOne
    @JoinColumn(name="id")
    private Student student;

    //==비즈니스 로직==//

    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + itemName + "', price=" + price + '}';
    }

//    public static Item createOrderItem(Item item, int itemPrice, int count){
//        Item orderItem = new Item();
//        orderItem.setPrice(itemPrice);
//        orderItem.setCount(count);
//        item.removeStock(count);
//        return orderItem;
//    }


}
