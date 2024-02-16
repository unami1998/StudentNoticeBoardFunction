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
    public BoardDTO() {
        this.id=id;
        this.title=title;
        this.content=getContent();
    }
    public BoardDTO(String title, String content) {
        this.title=title;
        this.content=content;
    }
}
