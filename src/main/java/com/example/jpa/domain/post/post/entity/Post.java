package com.example.jpa.domain.post.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 이 클래스가 JPA 엔티티임을 나타냄
@Entity
@Setter
@Getter
public class Post {
    // 이 필드가 엔티티의 기본 키임을 나타냄
    @Id
    // 기본 키 생성 전략을 IDENTITY로 설정 (데이터베이스가 자동으로 생성)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //long -> null이 없다. Long -> 객체타입은 null이 가능함
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    // 이 필드가 데이터베이스 컬럼이며, 길이를 100으로 제한
    @Column(length = 100)
    private String title;
    // 이 필드가 데이터베이스 컬럼이며, 컬럼 타입을 TEXT로 설정
    @Column(columnDefinition = "TEXT")
    private String body;
}
