package com.sparta.w2homework.controller;

import com.sparta.w2homework.dto.BoardRequestDto;
import com.sparta.w2homework.entity.Board;
import com.sparta.w2homework.repository.BoardRepository;
import com.sparta.w2homework.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/api/auth/post")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }


    @GetMapping("/api/post")
    public List<Board> getBoard(){
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/post/{id}")
    public Optional<Board> findById(@PathVariable Long id) {
        return boardRepository.findById(id);
    }

    @DeleteMapping("/api/auth/post/{id}")
    public String deleteBoard(@PathVariable Long id){
        boardRepository.deleteById(id);
        return "삭제완료";
    }

    @PutMapping("/api/auth/post/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        boardService.update(id, requestDto);
        return "수정완료";
    }

}
