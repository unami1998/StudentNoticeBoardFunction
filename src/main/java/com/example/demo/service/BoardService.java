package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void save(String title, String content, String filePath) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(title);
        boardDTO.setContent(content);
        boardDTO.setFilePath(filePath);

        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setFilePath(boardDTO.getFilePath());
        boardRepository.save(board);
    }

//    public List<BoardDTO> viewList() {
//        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();
//        List<Board> boardList = boardRepository.findAll(); // 실제로 데이터베이스에서 게시물 목록을 가져옴
//        BoardDTO boardDTO;
//        for (Board board : boardList) {
//            boardDTO = new BoardDTO();
//            boardDTO.setId(board.getId()); // id 속성 설정
//            boardDTO.setTitle(board.getTitle());
//            boardDTO.setContent(board.getContent());
//            boardDTOList.add(boardDTO);
//        }
//        System.out.println("test all " + boardRepository.findAll());
//        return boardDTOList;
//    }


//    public Page<BoardDTO> getBoard(Pageable pageable) {
//        Page<Board> boardPage = boardRepository.findAll(pageable);
//        return boardPage.map(new Function<Board, BoardDTO>() {
//            @Override
//            public BoardDTO apply(Board board) {
//                BoardDTO newBoardDTO = new BoardDTO();
//                newBoardDTO.setContent(board.getContent());
//                newBoardDTO.setTitle(board.getTitle());
//                newBoardDTO.setId(board.getId());
//                return newBoardDTO;
//            }
//        });
//    }

    public List<BoardDTO> getAllBoards() {

        List<Board> boardList = boardRepository.findAll(); // 실제로 데이터베이스에서 게시물 목록을 가져옴
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDTO boardDTO= new BoardDTO();
            boardDTO.setId(board.getId()); // id 속성 설정
            boardDTO.setTitle(board.getTitle());
            boardDTO.setContent(board.getContent());
            boardDTO.setFilePath(board.getFilePath());
            boardDTOList.add(boardDTO);
        }
        System.out.println("test all " + boardRepository.findAll());
        return boardDTOList;
    }


//    public Page<BoardDTO> paging(Pageable pageable) {
//        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
//        // int page = pageable.getPageNumber();
//        int pageLimit = 10; // 한페이지에 보여줄 글 개수
//
//        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
//        Page<Board> postsPages = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
//
//        //postsPages.getContent();
//        //postsPages.getTotalPages();
//
//        // 목록 : id, title, content, author
//        Page<BoardDTO> boardDTOS = postsPages.map(
//                board -> new BoardDTO(board.getTitle(),board.getContent()));
//
//        return boardDTOS;
//    }


}
