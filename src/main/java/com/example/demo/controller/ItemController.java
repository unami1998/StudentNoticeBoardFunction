package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping("/items/new")
    public ResponseDTO viewPersonInfo(@RequestParam("item_id") int id){
        List<Item> item = itemService.findItems(id);
        ItemDto itemDto = new ItemDto();
        itemDto.setPrice(itemDto.getPrice());
        System.out.println("id값은->" + itemDto.getId() + "이름은" + itemDto.getItemName()
            +"가격은" + itemDto.getPrice() + "입니다");
        return ResponseDTO.of();
    }
    @GetMapping("/allItem")
    public List<Item> AllInfo(){
        List<Item> items = itemService.getAllItems();
        return items;
    }
    @GetMapping("/items/{item_id}/edit")
    public ResponseDTO updateItem(@RequestParam("item_id") int id,
                                  @RequestParam("itemName") String name){
        itemService.updateItemName(id, name);
        return ResponseDTO.test("작업 중");
    }
    @PostMapping("/addItem") //db에 추가하기
    public ResponseDTO createForm (@RequestBody ItemDto item) {
        item.setItemName(item.getItemName());
        item.setPrice(item.getPrice());
        item.setStockQuantity(item.getStockQuantity());
        itemService.saveItem(item);
        return ResponseDTO.test("작업 중"); //책 목록
    }

    @GetMapping("/items/addStock")
    public void addStock(@RequestParam("itemName") String itemName){
        itemService.addStockItem(itemName);
        System.out.print("해당 상품 이름으로 재고 하나 추가했습니다");
    }

}