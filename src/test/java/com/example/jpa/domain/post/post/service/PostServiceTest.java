package com.example.jpa.domain.post.post.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest //스프링부트 테스트에서는 이거 붙여야 함
@ActiveProfiles("test") //DB 프로파일을 application-test로 적용한다는 것. 접미어 test를 입력하면 됨
public class PostServiceTest {

    @Test
    public void t1() {
        System.out.println("test");
    }
}
