package com.example.jpa.domain.post.post.repository;

import com.example.jpa.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//repository를 이용해 DB에 데이터 저장 가능(JpaRepository가 기본적인 메소드를 가지고 있음)
//Long은 Post 엔티티의 primary 키의 타입임. 여기엔 원시타입 말고 래퍼클래스로 넣어야 함.
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);

    List<Post> findByTitleAndBody(String title, String body);

    List<Post> findByTitleLike(String title);

    Page<Post> findByTitleLike(String keyword, Pageable pageable);

    List<Post> findByOrderByIdDesc();

    List<Post> findTop2ByTitleOrderByIdDesc(String title1);

    Page<Post> findAll(Pageable pageable);

    List<Post> findByAuthorUsername(String user);
}
