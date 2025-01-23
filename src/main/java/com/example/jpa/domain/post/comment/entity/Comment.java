package com.example.jpa.domain.post.comment.entity;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.global.entity.BaseEntity;
import com.example.jpa.global.entity.BaseTime;
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
public class Comment extends BaseTime {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

//    @JoinColumn(name = "fk_post") //외래키 설정 fk_post라는 컬럼이 생김
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    // 이 필드가 데이터베이스 컬럼이며, 길이를 100으로 제한
    // 이 필드가 데이터베이스 컬럼이며, 컬럼 타입을 TEXT로 설정
    @Column(columnDefinition = "TEXT")
    private String body;
}
