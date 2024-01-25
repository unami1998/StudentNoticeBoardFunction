package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@Validated
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/items/new")
    public ResponseDTO viewPersonInfo(@RequestParam("item_id") int id){
        Item item = itemService.findItems(id);
        ItemDto itemDto = new ItemDto();
        itemDto.setPrice(item.getPrice()); // <--이런식으로 하면 프런트한테 값을 전달해준다
        System.out.println("id값은->" + item.getId() + "이름은" + item.getItemName()
            +"가격은" + item.getPrice() + "입니다");
        return ResponseDTO.of();
    }
    @GetMapping("/allItem")
    public ResponseDTO AllInfo(){
        List<Item> items = itemService.getAllItems();
        System.out.print("테스트해보자" + items);
        return ResponseDTO.test("작업 중");
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
        itemService.saveItem((Item) item);
        return ResponseDTO.test("작업 중"); //책 목록
    }
    ///////////페이징 처리를 해보고싶었다
    @GetMapping("/list")
    @ResponseBody
    public List<Item> GetItemList(@PageableDefault(page =0, size=3, sort="id") Pageable pageable){
        //page 0부터 시작할 때 3개의 값만 나오고, 정렬은 id로 한다
        return itemService.FindBooksBypageRequest(pageable);
    }

}