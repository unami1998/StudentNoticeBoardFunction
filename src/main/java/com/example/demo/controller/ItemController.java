package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    public ResponseDTO createForm(Model model){
        model.addAttribute("form", new ItemDto());
        return ResponseDTO.of();
    }

    @PostMapping("/addItem") //db에 추가하기
    public ResponseDTO createForm (@RequestBody Item item) {
        item.setItemName(item.getItemName());
        item.setPrice(item.getPrice());
        itemService.saveItem(item);
        return ResponseDTO.test("작업 중"); //책 목록
    }

    @GetMapping("/items")
    public ResponseDTO list(Model itemsform){
        List<Item> items = itemService.findItems();   //service에서 가져 온 다음에
        itemsform.addAttribute("items", items);    //items에 다 뿌려
        return ResponseDTO.test("작업 중");
    }
    @GetMapping("/items/{itemid}/edit")
    public ResponseDTO updateItem(@PathVariable("itemid") int itemId, Model model){
        Item item = (Item)itemService.findById(itemId); //itemId 반환
        ItemDto form = new ItemDto();
        form.setItemName(item.getItemName());
        return ResponseDTO.test("작업 중");
    }
//    @PostMapping("/items/{itemId}/edit")
//    public ResponseDTO updateItem(@RequestBody ){
//
//        //아이디로 이름 수정
//        return ResponseDTO.test("작업 중");
//    }

    //똥오줌 발싸!!
}