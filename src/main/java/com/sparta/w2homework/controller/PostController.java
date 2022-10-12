package com.sparta.w2homework.controller;

import com.sparta.w2homework.dto.PostRequestDto;
import com.sparta.w2homework.entity.Post;
import com.sparta.w2homework.repository.PostRepository;
import com.sparta.w2homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/api/auth/post")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }


    @GetMapping("/api/post")
    public List<Post> getPost(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/post/{id}")
    public Optional<Post> findById(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @DeleteMapping("/api/auth/post/{id}")
    public String deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return "삭제완료";
    }

    @PutMapping("/api/auth/post/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        postService.update(id, requestDto);
        return "수정완료";
    }

}
