package com.example.demo.dto;

import com.example.demo.entity.Item;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddRequestItemDto {
    private String itemName;
    private int itemPrice;

    public AddRequestItemDto(String itemName, int itemPrice) {
        this.itemPrice=itemPrice;
        this.itemName=itemName;
    }

    @Override
    public String toString(){
        return "name:" + itemName+ "Item price: "+ itemPrice + "over";
    }
    public Item toEntity(){
        Item item = new Item();
        item.setItemName(this.itemName);
        item.setItemPrice(this.itemPrice);
        return item;
    }

}
