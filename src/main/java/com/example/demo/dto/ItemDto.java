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
    private int price;
    private int itemCount;


    public ItemDto() {

    }

    @Override
    public String toString(){
        return "name:" + itemName+ "Item price: "+ price + "over";
    }
    //==생성 메서드 ==//

}
