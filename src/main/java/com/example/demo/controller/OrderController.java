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
        String itemName = orderDto.getItemName();
        String studentName = orderDto.getUser().getName();

        String result = orderService.orderItem(studentName, itemName);
        if (result.equals("주문 성공")) {
            System.out.println("주문 성공");
            return ResponseDTO.create(true, "주문 성공: 주문 ID -").toResponseDTO();
        } else if (result.equals("학생 정보 문제")) {
            System.out.println("주문 실패: 학생 정보 문제");
            return ResponseDTO.create(false, "상품 주문 실패: 학생 정보 문제").toResponseDTO();
        } else if (result.equals("상품 정보 문제")) {
            System.out.println("주문 실패: 상품 정보 문제");
            return ResponseDTO.create(false, "상품 주문 실패: 상품 정보 문제").toResponseDTO();
        } else {
            System.out.println("주문 실패: " + result);
            return ResponseDTO.create(false, "상품 주문 실패: " + result).toResponseDTO();
        }
    }
}
