package com.sparta.w2homework.controller;

import com.sparta.w2homework.dto.CommentRequestDto;
import com.sparta.w2homework.entity.Board;
import com.sparta.w2homework.entity.Comment;
import com.sparta.w2homework.repository.BoardRepository;
import com.sparta.w2homework.repository.CommentRepository;
import com.sparta.w2homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final BoardRepository boardRepository;

    @PostMapping("/api/auth/comment")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto){

        Board board = boardRepository.findById(commentRequestDto.getPostid()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        Comment comment = new Comment(commentRequestDto, board);
        return commentRepository.save(comment);
    }

    @GetMapping("/api/comment")
    public List<Comment> getComment(){
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }

    @PutMapping("/api/auth/comment/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto ){
        commentService.update(id, commentRequestDto);
        return "수정완료";
    }


    @DeleteMapping("/api/auth/comment/{id}")
    public String deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
        return  "삭제완료";
    }
}
