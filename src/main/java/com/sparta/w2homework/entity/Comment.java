package com.sparta.w2homework.entity;

import com.sparta.w2homework.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


//@Builder //
@Getter //
//@AllArgsConstructor//
@NoArgsConstructor //기본생성자
//@Table(name = "comment")
@Entity //
public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;



    public Comment(CommentRequestDto commentRequestDto, Post post) {
        this.comment = commentRequestDto.getComment();
        this.username = commentRequestDto.getUsername();
        this.post = post;

    }

    public void update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
        this.username = requestDto.getUsername();
    }


}
