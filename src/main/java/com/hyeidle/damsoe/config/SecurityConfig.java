package com.hyeidle.damsoe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 보안 설정을 정의한다.
 */
@Configuration
public class SecurityConfig {
	/**
	 * 비밀번호 암호화를 위한 BCryptPasswordEncoder를 제공한다.
	 *
	 * @return BCryptPasswordEncoder 인스턴스
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * HTTP 보안 설정을 구성한다.
	 *
	 * @param http HttpSecurity 객체
	 * @return SecurityFilterChain 객체
	 * @throws Exception 설정 중 발생 가능한 예외
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화 (API용)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/auth/**", "/swagger-ui/**", "/api-docs/**").permitAll()
				.requestMatchers("/app.html", "/css/**", "/js/**").permitAll() //
				.anyRequest().authenticated() // 나머지는 인증 필요
			);
		return http.build();
	}
}
