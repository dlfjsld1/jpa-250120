package com.example.jpa.domain.post.tag.entity;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tag  {

    @EmbeddedId //@EmbeddedId는
    private TagId id;

//    @Column(length = 100)
//    @EqualsAndHashCode.Include
//    @MapsId("name")
//    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Include
    @MapsId("postId")
    private Post post;
}