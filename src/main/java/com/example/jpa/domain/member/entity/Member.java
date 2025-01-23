package com.example.jpa.domain.member.entity;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.global.entity.BaseEntity;
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
public class Member extends BaseEntity {

    @Column(length = 100, unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String nickname;
}
