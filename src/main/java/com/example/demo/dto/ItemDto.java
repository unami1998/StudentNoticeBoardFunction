package com.example.demo.dto;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemDto {
    private String itemName;
    private int price;
    private int itemCount;
    private int id;
    private int StockQuantity;

    public ItemDto() {

    }

    @Override
    public String toString(){
        return "name:" + itemName+ "Item price: "+ price + "over";
    }

}
