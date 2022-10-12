package com.sparta.w2homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sparta.w2homework.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;

    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Post(PostRequestDto postDto) {
        this.title = postDto.getTitle();
        this.contents = postDto.getContents();
        this.username = postDto.getUsername();
    }

    public void update(PostRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.username=requestDto.getUsername();
    }
}