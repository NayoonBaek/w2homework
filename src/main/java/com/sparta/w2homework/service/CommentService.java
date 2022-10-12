package com.sparta.w2homework.service;


import com.sparta.w2homework.dto.CommentRequestDto;
import com.sparta.w2homework.entity.Comment;
import com.sparta.w2homework.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public Long update(Long postid, CommentRequestDto requestDto){
         Comment comment = commentRepository.findById(postid).orElseThrow(
                 ()->new IllegalArgumentException("아이디가 존재하지 않습니다.")
         );
         comment.update(requestDto);
         return comment.getId();
    }






}
