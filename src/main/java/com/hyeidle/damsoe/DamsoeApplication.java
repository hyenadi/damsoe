package com.hyeidle.damsoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DamsoeApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
			System.out.println("Loaded: " + entry.getKey() + "=" + entry.getValue()); // 디버깅용
		});
		SpringApplication.run(DamsoeApplication.class, args);
	}
}
