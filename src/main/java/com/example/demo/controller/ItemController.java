package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.entity.Item;
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
    @GetMapping("/itemlist")   //모든 회원 리스트 뿌리기
    public List<Item> GetAllMember(){
        return itemService.getAllMembers();
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
    @GetMapping("/items/{item_id}/edit")  //특정 아이디 값으로 해당 id 값 name 지우기
    public ResponseDTO updateItem(@RequestParam("item_id") int id,
                                  @RequestParam("itemName") String name){
        itemService.updateItemName(id, name);
        return ResponseDTO.test("작업 중");

    }
}