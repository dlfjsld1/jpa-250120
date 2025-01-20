package com.example.jpa.domain.post.post.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 이 클래스가 JPA 엔티티임을 나타냄
@Entity
@Getter
@Setter
//빌더가 내부적으로 무조건 @AllArgsConstructor를 사용해야 함
@AllArgsConstructor
//@AllArgsConstructor를 하면 기본생성자를 안 만들어주니 @NoArgsConstructor를 넣어야 함
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post {

    // 이 필드가 엔티티의 기본 키임을 나타냄
    @Id
    // 기본 키 생성 전략을 IDENTITY로 설정 (데이터베이스가 자동으로 생성)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id; //long -> null이 없다. Long -> 객체타입은 null이 가능함
    @CreatedDate //생성시간 jpa가 자동입력. @EntityListeners로 감시 필요
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdDate;
    @LastModifiedDate //수정시간 jpa가 자동입력
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedDate;
    // 이 필드가 데이터베이스 컬럼이며, 길이를 100으로 제한
    @Column(length = 100)
    private String title;
    // 이 필드가 데이터베이스 컬럼이며, 컬럼 타입을 TEXT로 설정
    @Column(columnDefinition = "TEXT")
    private String body;

}
