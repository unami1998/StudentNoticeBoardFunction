package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class boardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }
    //내용이 넘어오는 것을 확인
    @PostMapping("/board/writepro") //매개변수로 들어오는 것 작성
    public String boardWritePro(String title, String content){
        System.out.println("제목 : " + title);
        System.out.println("내용 : " + content);
        return "";
    }
}
