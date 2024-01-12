package com.example.demo.dto;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.StudentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter
public class ItemDto {
    private String itemName;
    private int itemPrice;

    public ItemDto(String itemName, int itemPrice) {
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
        return item;
    }

}
