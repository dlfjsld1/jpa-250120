package com.example.jpa.global;

import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.service.CommentService;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final CommentService commentService;

    // 프록시 객체를 획득
    @Autowired
    @Lazy
    private BaseInitData self; // 프록시


    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work2() {
        Post post = postService.findById(1L).get();
        //        Comment comment = commentService.findById(1L).get();

        int count = post.getComments().size(); // 3개
        System.out.println(count);
        post.removeComment(1L);
    }

    @Transactional
    public void work1() {
        if (postService.count() > 0) {
            return;
        }

        Post p1 = postService.write("title1", "body1");

        Comment c1 = Comment.builder()
                .body("comment1")
                .build();

        c1 = commentService.save(c1); //insert 바로 실행
        p1.addComment(c1);

        Comment c2 = Comment.builder()
                .body("comment2")
                .build();

        c2 = commentService.save(c2);
        p1.addComment(c2);

        Comment c3 = Comment.builder()
                .body("comment2")
                .build();
        c3 = commentService.save(c3);
        p1.addComment(c3);


        // Check if p1 and c3 are in a persistent relationship
        System.out.println("Is p1 persistent? " + (p1.getId() != null));
        System.out.println("Is c3 persistent? " + (c3.getId() != null));
        System.out.println("Is c3 associated with p1? " + p1.getComments().contains(c3));

        p1.removeComment(c1);

//        p1.getComments().add(c1); // 관계의 주인이 DB 반영을 한다.
//        commentService.write(p1, "comment1");
    }
}