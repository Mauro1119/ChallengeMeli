package com.meli.quasar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.quasar.infrastructure.controllers.MessagesController;


@SpringBootTest
class ChallengeApplicationTests {
@Autowired
private MessagesController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
