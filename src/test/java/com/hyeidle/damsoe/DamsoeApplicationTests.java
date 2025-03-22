package com.hyeidle.damsoe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.hyeidle.damsoe.config.DotenvInitializer;

@SpringBootTest
@ContextConfiguration(initializers = DotenvInitializer.class)
class DamsoeApplicationTests {

	@Value("${spring.datasource.url}")
	String dbUrl;

	@Test
	void contextLoads() {

	}

}
