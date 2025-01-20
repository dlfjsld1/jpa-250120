package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post write(String title, String body) {
        //1. Post 조립
        Post post = Post.builder()
                .title(title)
                .body(body)
                .build();

        //2. repository에 넘김 + DB 반영
        postRepository.save(post);

        return post;
    }

    public Post modify(Post post, String title, String body) {
        post.setTitle(title);
        post.setBody(body);
        return postRepository.save(post);
    }

    //@Transactional을 쓰면 postRepository.save(post) 같은 걸로 커밋 할 필요 없이 인스턴스 조작만으로 트랜잭션이 된다.
    //단, post 객체를 그냥 받아와 조작하는건 안 되고
    //메소드 내에서 직접 찾아서 처리할 때만 작동함!
    //jpa는 자카르타 프레임워크를 사용하나 @Transactional은 예외로 스프링 프레임워크를 사용해야 함
    @Transactional
    public Post modify2(long id, String title, String body) {
        Post post = postRepository.findById(id).get();
        post.setTitle(title);
        post.setBody(body);
        return postRepository.save(post);
    }

    public long count() {
        return postRepository.count();
    }


    public Optional<Post> findById(long id) {
        return postRepository.findById(id);

    }
}
