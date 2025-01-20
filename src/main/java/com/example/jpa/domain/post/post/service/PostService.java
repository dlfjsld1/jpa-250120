package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post write(String title, String body) {
        //1. Post 조립
        Post post = new Post();
        //id는 자동생성
        //post.setId(1L);
        post.setCreatedDate(LocalDateTime.now());
        post.setModifiedDate(LocalDateTime.now());
        post.setTitle(title);
        post.setBody(body);

        //2. repository에 넘김 + DB 반영
        postRepository.save(post);

        return post;
    }



}
