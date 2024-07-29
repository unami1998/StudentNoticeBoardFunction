package com.example.demo.controller;


import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.BoardsearchDTO;
import com.example.demo.dto.MyAccountInfoDTO;
import com.example.demo.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;


@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private StudentController studentController;

    @PostMapping("/{id}/favorite")
    public ResponseEntity<String> incrementFavorite(@PathVariable Long id) {
        boardService.incrementFavorite(id);
        return ResponseEntity.ok("Favorite count incremented");
    }

    @GetMapping("/searchResults")
    public ResponseEntity<List<BoardsearchDTO>> search(@RequestParam String input, Model model){
        List<BoardsearchDTO> searchResults = boardService.searchInputString(input);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/ten_minit_lover")
    public String lover(Model model){
        String lover = boardService.tenMinitLover();
        return lover;
    }

    @GetMapping("/home")
    public String home(Model model){
        List<BoardDTO> boards  = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "home";
    }

    @GetMapping("/writePage")
    public String writeForm() {
        return "writePage"; // login.html 템플릿 반환
    }

    @PostMapping("/writeSave")
    public String boardWritePro(String title, String content,
                                @RequestParam("file") MultipartFile file,
                                HttpSession session,
                                Model model) { // @RequestBody 제거
        MyAccountInfoDTO currentUser = (MyAccountInfoDTO) session.getAttribute("currentUser");
        if (currentUser == null) {
            model.addAttribute("error", "로그인 정보가 유효하지 않습니다.");
            return "index"; // 로그인 페이지로 리다이렉트
        }
        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            model.addAttribute("showModal", true); // 모달 표시
            return "writePage"; // 입력 페이지로 다시 돌아감
        }

        String filePathString = null;
        try {
            if (!file.isEmpty()) { //파일이 있다
                String uploadDir = "src/main/resources/static/uploads";
                Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

                // 정규화된 파일 이름 가져오기
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                // 파일 경로 설정
                Path filePath = uploadPath.resolve(fileName);
                File destinationFile = filePath.toFile();
                // 디렉토리가 존재하지 않으면 생성
                if (!destinationFile.getParentFile().exists()) {
                    destinationFile.getParentFile().mkdirs();
                }
                // 파일 저장
                file.transferTo(destinationFile);
                filePathString = filePath.toString();
                Path finalFilePath = Paths.get(filePathString);
                Long userId = currentUser.getId();

                boardService.save(title, content, finalFilePath, userId);
                // 파일 저장 후 서비스에 저장 요청
            }
            Long userId = currentUser.getId();
            boardService.save(title, content, null, userId); //파일 없으면 null로 저장해
            return "redirect:/home/board";
        }catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("showModal3", true);
            return "writePage";
        }
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
