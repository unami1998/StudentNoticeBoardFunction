package com.example.demo.repository;

import com.example.demo.entity.Board;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b WHERE b.content LIKE %:input% OR b.title LIKE %:input%")
    List<Board> findByContentOrTitleContaining(String input);

   List<Board> findByStudent_Id(Long studentId);

    Optional<Board> findTopByCreateDateAfterOrderByPointLoveDesc(LocalDateTime oneHourAgo);

    Board findTopByOrderByPointLoveDesc();
}
