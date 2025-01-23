package com.example.jpa.domain.post.post.entity;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.tag.entity.Tag;
import com.example.jpa.domain.post.tag.entity.TagId;
import com.example.jpa.global.entity.BaseEntity;
import com.example.jpa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

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
public class Post extends BaseTime {

    // 이 필드가 데이터베이스 컬럼이며, 길이를 100으로 제한
    @Column(length = 100)
    private String title;

    // 이 필드가 데이터베이스 컬럼이며, 컬럼 타입을 TEXT로 설정
    @Column(columnDefinition = "TEXT")
    private String body;


    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Builder.Default//comment에 사용됐잖아. 사용하지 않는 쪽이 주인임 즉 포스트가 주인
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;


    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default // 이거 안 넣으면 빌더가 new ArrayList<>()된걸 무시함
    private List<Tag> tags = new ArrayList();


    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void addTag(String name) {
//        Optional<Tag> oldTag = tags.stream()
//                .filter(t -> t.getName().equals(name))
//                .findFirst();
//
//        if (oldTag.isPresent()) {
//            return;
//        }

        Tag tag = Tag.builder()
                .id(new TagId(this.getId(), name))
                .post(this)
                .build();
        tags.add(tag);
    }
}
