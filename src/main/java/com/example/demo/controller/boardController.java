package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class boardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        System.out.println("test");
        return "save";
    }
//    @PostMapping("/save")
//    public String boardWritePro(String title, String content) { // @RequestBody 제거
//        System.out.println("제목: " + title); // getContent()를 사용하여 내용을 출력해야 합니다.
//        boardService.save(title, content);
//        return "redirect:/board/paging";
//    }

//    // @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
//    @GetMapping("/paging")
//    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        Page<BoardDTO> boardList = boardService.paging(pageable);
//        int blockLimit = 5;
//        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
//        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
//
//        // page 갯수 20개
//        // 현재 사용자가 3페이지
//        // 1 2 3
//        // 현재 사용자가 7페이지
//        // 7 8 9
//        // 보여지는 페이지 갯수 3개
//        // 총 페이지 갯수 8개
//
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        return "paging";
//    }
}
