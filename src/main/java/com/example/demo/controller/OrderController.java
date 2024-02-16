package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/order")
    public ResponseDTO orderItem(@RequestBody ItemDto itemDto, Long id) throws Exception {
        try{
            orderService.orderItem(itemDto, id);

        }catch (Exception e){

        }
        return ResponseDTO.test("주문한 상품 이름" + itemDto.getItemName() + ")");
    }
}
