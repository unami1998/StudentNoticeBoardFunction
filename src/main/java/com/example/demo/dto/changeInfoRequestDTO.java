package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class changeInfoRequestDTO {
    private Long id;
    private String name;
    private String nickName;
    private String address;
    private Integer age;
    private String grade;
    private int pointCount;
}
