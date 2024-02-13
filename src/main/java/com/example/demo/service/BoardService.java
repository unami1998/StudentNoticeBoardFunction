package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

//    public void write(BoardDTO boardDTO) {
//        Board board = new Board();
//        board.setTitle(boardDTO.getTitle());
//        board.setContent(boardDTO.getContent());
//        boardRepository.save(board);
//    }

    public void save(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        boardRepository.save(board);
    }
}
