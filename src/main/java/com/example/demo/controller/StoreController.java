package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Slf4j
public class StoreController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/store")
    public ResponseDTO findStudent(@RequestParam(defaultValue = "name") String name,
                                   @RequestParam(defaultValue = "3") int age) {
        log.info("name={}, age={}", name, age);
        return ResponseDTO.of();
    }

    @PostMapping("/addItem") //db에 추가하기
    public ResponseDTO createForm (ItemDto p1) {

        Item item = new Item();
        item.setItemName(p1.getItemName());
        item.setPrice(p1.getItemPrice());

        System.out.println(p1.toString());

        //dto를 entity로 변환
        //reposi에게 ENtity를 db에 저장하게
        itemRepository.save(item);
        System.out.print("저장해버리자?" + item.toString());
        return ResponseDTO.test("작업 중" + p1);
    }
}