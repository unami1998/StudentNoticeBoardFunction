package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

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

    @JsonProperty("stockQuantity")
    private int stockQuantity; //재고수량


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    public Item() {
        this.stockQuantity = 5;
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
