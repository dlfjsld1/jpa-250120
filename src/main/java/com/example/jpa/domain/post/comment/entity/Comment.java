package com.example.jpa.domain.post.comment.entity;

import com.example.jpa.domain.post.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)// 이 엔티티에 대한 Auditing(자동으로 값 매핑) 기능을 활성화
public class Comment {

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

//    @JoinColumn(name = "fk_post") //외래키 설정 fk_post라는 컬럼이 생김
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    // 이 필드가 데이터베이스 컬럼이며, 길이를 100으로 제한
    // 이 필드가 데이터베이스 컬럼이며, 컬럼 타입을 TEXT로 설정
    @Column(columnDefinition = "TEXT")
    private String body;
}
