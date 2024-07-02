package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
public class boardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/home")
    public String home(Model model){
        List<BoardDTO> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "home";
    }




    @GetMapping("/writePage")
    public String writeForm() {
        return "writePage"; // login.html 템플릿 반환
    }

    @PostMapping("/writeSave")
    public String boardWritePro(String title, String content, @RequestParam("file") MultipartFile file, Model model) { // @RequestBody 제거
//        long boardResult = boardService.writeBoard(title, content);
        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            model.addAttribute("showModal", true); // 모달 표시
            return "writePage"; // 입력 페이지로 다시 돌아감
        }

        String filePath = null;
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                // 파일을 저장할 경로 설정
                filePath = "C:\\uploads\\" + fileName;
                File destinationFile = new File(filePath);

                // 디렉토리가 존재하지 않으면 생성
                File parentDir = destinationFile.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }

                file.transferTo(destinationFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("showModal3", true);
            return "writePage";
        }
        boardService.save(title, content, filePath);
        return "home";
    }


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
