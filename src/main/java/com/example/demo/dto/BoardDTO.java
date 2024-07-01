package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String filePath;

    public BoardDTO() {

    }
}
