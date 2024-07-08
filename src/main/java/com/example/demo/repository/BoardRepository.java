package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b WHERE b.content LIKE %:input% OR b.title LIKE %:input%")
    List<Board> findByContentOrTitleContaining(String input);

    List<Board> findByStudentId(Long id);
}
