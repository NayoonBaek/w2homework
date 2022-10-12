package com.sparta.w2homework.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String username;
    private String comment;
    private Long postid;
}


