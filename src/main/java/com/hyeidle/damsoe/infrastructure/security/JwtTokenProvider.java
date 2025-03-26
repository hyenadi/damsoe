package com.hyeidle.damsoe.infrastructure.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

	private SecretKey key;

	private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15;
	private final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7;


	@PostConstruct
	public void initKey() {
		String secret = System.getProperty("JWT_SECRET");
		if (secret == null || secret.isEmpty()) {
			secret = System.getenv("JWT_SECRET");
		}
		if (secret == null || secret.isEmpty()) {
			throw new IllegalArgumentException("JWT_SECRET 값이 없습니다!");
		}
		System.out.println("JWT_SECRET 값 로드됨: " + secret);
		key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String generateAccessToken(Long userId, String email) {
		return Jwts.builder()
			.subject(userId.toString())
			.claim("email", email)
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
			.signWith(key)
			.compact();
	}

	public String generateRefreshToken(Long userId) {
		return Jwts.builder()
			.subject(userId.toString())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
			.signWith(key)
			.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Claims getClaims(String token) {
		return Jwts.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	public Long getUserId(String token) {
		return Long.valueOf(getClaims(token).getSubject());
	}
}