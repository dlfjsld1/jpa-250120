package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post write(Member author, String title, String body) {
        //1. Post 조립
        Post post = Post.builder()
                .author(author)
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

    //@Transactional을 쓰면 postRepository.save(post) 같은 걸로 커밋 할 필요 없이 객체 조작만으로 트랜잭션이 된다.
    //단, post 객체를 그냥 받아와 조작하는건 안 되고
    //메소드 내에서 직접 찾아서 처리할 때만 작동함!
    //왜냐하면 repository가 제공하는 기본기능은 자신만의 트랜잭션을 가지고 있다.
    //db에서 데이터를 가져올 때 JPA 임시 저장소(영속성 컨텍스트)에 스냅샷을 떠놓고 가져온다.
    //(영속성 컨텍스트는 트랜잭션 기간 동안에만 존재한다. 트랜잭션이 끝나면(commit) 영속성 컨텍스트는 사라짐)
    //그런데 그냥 post 객체를 받아와 조작하면 스냅샷과 달라지기 때문에
    //스냅샷과 달라진 부분을 감지하고 업데이트를 해준다.
    //원래는 findById 하는 부분에서 트랜잭션이 끝나야 하지만,
    //@Transactional 어노테이션은 이러한 트랜잭션의 범위를 해당 메서드 전체로 확장해준다.
    //트랜잭션은 더 큰 트랜잭션 안에 편입된다
    //jpa는 자카르타 프레임워크를 사용하나 @Transactional은 예외로 스프링 프레임워크를 사용해야 함
    @Transactional
    public void modify2(long id, String title, String body) {
        Post post = postRepository.findById(id).get();
        post.setTitle(title);
        post.setBody(body);
    }

    public long count() {
        return postRepository.count();
    }


    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByTitleAndBody(String title, String body) {
        return postRepository.findByTitleAndBody(title, body);
    }

    public List<Post> findByTitleLike(String title) {
        return postRepository.findByTitleLike(title);
    }

    public Page<Post> findByTitleLike(String keyword, Pageable pageable) {
        return postRepository.findByTitleLike(keyword, pageable);
    }

    public List<Post> findByOrderByIdDesc() {
        return postRepository.findByOrderByIdDesc();
    }

    public List<Post> findTop2ByTitleOrderByIdDesc(String title1) {
        return postRepository.findTop2ByTitleOrderByIdDesc(title1);
    }


    public List<Post> findByAuthorUsername(String user) {
        return postRepository.findByAuthorUsername(user);
    }
}
