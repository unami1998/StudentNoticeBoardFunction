package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {
    private MyAccountInfoDTO user;

    private Long id;
    private String title;
    private String content;
    private String filePath;
    private LocalDateTime createDate;
    public BoardDTO() {

    }

    public BoardDTO(Long id, String title, String content, String filePath, LocalDateTime createDate) {
    }
}
