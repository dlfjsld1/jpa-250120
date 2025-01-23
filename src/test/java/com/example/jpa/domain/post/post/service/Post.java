package com.example.jpa.domain.post.post.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
    //테스트용 포스트

    @EqualsAndHashCode.Include
    private long id;
    private String title;

}
