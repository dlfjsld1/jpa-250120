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
        Post post = Post.builder()
                .title(title)
                .body(body)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        //2. repository에 넘김 + DB 반영
        postRepository.save(post);

        return post;
    }

    public long count() {
        return postRepository.count();
    }



}
