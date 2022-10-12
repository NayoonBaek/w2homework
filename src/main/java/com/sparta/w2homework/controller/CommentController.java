package com.sparta.w2homework.controller;

import com.sparta.w2homework.dto.CommentRequestDto;
import com.sparta.w2homework.entity.Comment;
import com.sparta.w2homework.entity.Post;
import com.sparta.w2homework.repository.CommentRepository;
import com.sparta.w2homework.repository.PostRepository;
import com.sparta.w2homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final PostRepository postRepository;

    @PostMapping("/api/auth/comment")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto){
        Post post = postRepository.findById(commentRequestDto.getPostid()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        Comment comment = new Comment(commentRequestDto, post);
        return commentRepository.save(comment);
    }

    @GetMapping("/api/comment")
    public List<Comment> getComment(){
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }

    @PutMapping("/api/auth/comment/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto ){
        commentService.update(id, commentRequestDto);
        return "댓글수정완료";
    }


    @DeleteMapping("/api/auth/comment/{id}")
    public String deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
        return  "댓글삭제완료";
    }
}
