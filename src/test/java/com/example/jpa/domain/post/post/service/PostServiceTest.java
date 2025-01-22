package com.example.jpa.domain.post.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest //스프링부트 테스트에서는 이거 붙여야 함. 스프링 테스트환경은 스프링에 붙어있음
@ActiveProfiles("test") //DB 프로파일을 application-test로 적용한다는 것. 접미어 test를 입력하면 됨
public class PostServiceTest {

    @Autowired //테스트에서는 private final PostService postService가 안 먹힘 룰이 그럼
    private PostService postService;

    @Test
    @DisplayName("글 2개 작성")
    @Transactional //이거 붙이면 DB에 반영 안 됨
    //@Rollback은 기본적으로 @Transactional에 들어있지만 @Rollback(false) 이렇게 하면 DB에 반영됨
    //@Rollback(false)
    public void t1() {
        postService.write("title11", "body11");
        postService.write("title22", "body22");
    }
}
