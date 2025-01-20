package com.example.jpa.global;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    @Order(1) //@Configuration 안에서 존재하는 빈들의 순서 설정
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("applicationRunner");
            //샘플데이터 3개 생성.
            //데이터 3개가 이미 있으면 패스
            if(postService.count() >= 3) return;
            Post p1 = postService.write("title1", "body1");
            System.out.println(p1.getId() + "번 포스트가 생성되었습니다.");
            Post p2 = postService.write("title2", "body2");
            System.out.println(p2.getId() + "번 포스트가 생성되었습니다.");
            Post p3 = postService.write("title3", "body3");
            System.out.println(p3.getId() + "번 포스트가 생성되었습니다.");
            System.out.println("Initialized");
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return args -> {
            System.out.println("Modify initializing...");
            Thread.sleep(1000);
            postService.modify2(1L, "new title", "new body");
            System.out.println("Modify initialized");
        };
    }
}

