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
    public responseDto findStudent(@RequestParam(defaultValue = "name") String name,
                                   @RequestParam(defaultValue = "3") int age) {
        log.info("name={}, age={}", name, age);
        return responseDto.of();
    }

    @PostMapping("/addItem") //db에 추가하기
    public responseDto addItem(ItemDto p1) {
        System.out.println(p1.toString());
        //dto를 entity로 변환
        Item item = p1.toEntity();
        //reposi에게 ENtity를 db에 저장하게
        Item savedItem = itemRepository.save(item);
        System.out.print("저장해버리자?" + savedItem.toString());
        return responseDto.test("작업 중" + p1);
    }
}