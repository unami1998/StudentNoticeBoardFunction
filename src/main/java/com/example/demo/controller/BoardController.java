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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;


    @PostMapping("/{id}/favorite")
    public ResponseEntity<String> incrementFavorite(@PathVariable Long id) {
        boardService.incrementFavorite(id);
        return ResponseEntity.ok("좋아요를 눌렀습니다.");
    }


    @GetMapping("/searchResults")
    public ResponseEntity<List<BoardsearchDTO>> search(@RequestParam String input, Model model) {
        List<BoardsearchDTO> searchResults = boardService.searchInputString(input);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        List<BoardDTO> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);

        String topLoverTitle = boardService.getTopLover();
        model.addAttribute("topLoverTitle", topLoverTitle);

        MyAccountInfoDTO user = (MyAccountInfoDTO) session.getAttribute("currentUser");
        if (user == null) {
            // user가 null인 경우 로그인 페이지로 리디렉션하거나 적절한 처리를 합니다.
            return "index";  // 로그인 페이지로 리디렉션 예시
        }
        String nickName = user.getNickName();
        if (session.getAttribute("redirected") == null) {
            session.setAttribute("redirected", true);
            return "redirect:/board/home?nick_name=" + nickName;
        }
        session.removeAttribute("redirected");

        return "home";
    }

    @GetMapping("/writePage")
    public String writeForm(HttpSession session, Model model) {
        MyAccountInfoDTO user = (MyAccountInfoDTO) session.getAttribute("currentUser");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "writePage"; // login.html 템플릿 반환
    }

    @PostMapping("/writeSave")
    public String boardWritePro(@ModelAttribute BoardDTO boardDTO,
                                @RequestParam("file") MultipartFile file,
                                HttpSession session,
                                Model model) throws IOException { // @RequestBody 제거
        MyAccountInfoDTO user = (MyAccountInfoDTO) session.getAttribute("currentUser");
        LocalDateTime time = LocalDateTime.now();
        boardDTO.setCreateDate(time);
        if (boardDTO.getTitle() == null || boardDTO.getTitle().isEmpty() || boardDTO.getContent() == null || boardDTO.getContent().isEmpty()) {
            model.addAttribute("showModal", true); // 모달 표시
            model.addAttribute("user", user);

            return "writePage"; // 입력 페이지로 다시 돌아감
        }
        if (user == null || user.getId() == 0L) {
            model.addAttribute("error", "로그인 정보가 유효하지 않습니다.");
            return "writePage"; // 로그인 페이지로 리다이렉트
        }
 //       String filePathString = null;

        if (!file.isEmpty()) { // 파일이 있는 경우
            String UPLOAD_DIR = "src/main/resources/static/uploads/";
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // 디렉토리가 존재하지 않으면 생성
            }

            Path filePath = uploadPath.resolve(fileName);
            byte[] bytes = file.getBytes();
            Files.write(filePath, bytes);
            String webPath = "/uploads/" + fileName;

            boardDTO.setFilePath(webPath);
        } else {
            boardDTO.setFilePath(null);
        }
        boardService.save(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getFilePath(), user.getId(), boardDTO.getCreateDate());
        return "redirect:/board/home?nick_name=" + user.getNickName();
    }
}


// @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
//    @GetMapping("/paging")
//    public String paging(@PageableDefault(page = 0, size = 7) Pageable pageable, Model model) {
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
//        model.addAttribute("boardList", boardList.getContent());
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("currentPage", pageable.getPageNumber() + 1);
//        model.addAttribute("totalPages", boardList.getTotalPages());
//        return "home";
//    }

