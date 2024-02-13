package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class boardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }
    //내용이 넘어오는 것을 확인
    @PostMapping("/save")
    public String boardWritePro(@RequestBody BoardDTO boardDTO){
        boardService.save(boardDTO);
        System.out.println("제목" +boardDTO.getContent());
        return "";
    }
}
