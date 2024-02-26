package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/order")
    public ResponseDTO orderItem(@RequestBody OrderDto orderDto) throws Exception {
            orderService.orderItem(orderDto.getItemName(), orderDto.getId());
            System.out.println("주문 성공");

        return ResponseDTO.test("주문한 상품 이름" + orderDto.getItemName() + ")");
    }
}
