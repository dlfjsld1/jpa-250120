package com.example.jpa;

import com.example.jpa.domain.post.post.service.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class JpaApplicationTests {

	@Test
	void t1() {
		Post p1 = new Post(1, "title1");
		Post p2 = new Post(1, "title1");

		Set<Post> posts = new HashSet<>(); //Set는 중복 허용하지 않음

		posts.add(p1);
		posts.add(p2);

		assertThat(posts.size()).isEqualTo(1);

//		assertThat(p1).isEqualTo(p2);
	}

}
