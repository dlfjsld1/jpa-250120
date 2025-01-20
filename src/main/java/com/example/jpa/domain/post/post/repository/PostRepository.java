package com.example.jpa.domain.post.post.repository;

import com.example.jpa.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//repository를 이용해 DB에 데이터 저장 가능
//Long은 Post 엔티티의 primary 키의 타입임. 여기엔 원시타입 말고 래퍼클래스로 넣어야 함.
public interface PostRepository extends JpaRepository<Post, Long> {

}
