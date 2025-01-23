package com.example.jpa.global.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseEntity {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Setter(AccessLevel.PRIVATE)
    private Long id; // long -> null X, Long -> null O
    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedDate;
}