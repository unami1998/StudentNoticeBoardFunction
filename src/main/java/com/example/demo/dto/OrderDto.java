package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private StudentDTO user;
    
    private Long id;
    private String itemName;
}
