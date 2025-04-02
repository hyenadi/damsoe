package com.hyeidle.damsoe.application.auth;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyeidle.damsoe.application.auth.dto.request.LoginRequest;
import com.hyeidle.damsoe.application.auth.dto.request.RegisterRequest;
import com.hyeidle.damsoe.application.auth.dto.response.LoginResponse;
import com.hyeidle.damsoe.application.auth.exception.EmailAlreadyExistsException;
import com.hyeidle.damsoe.domain.entity.User;
import com.hyeidle.damsoe.domain.repository.UserRepository;
import com.hyeidle.damsoe.infrastructure.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisTemplate<String, String> redisTemplate;

	@Transactional
	public String registerUser(RegisterRequest request) {
		if (userRepository.existsByEmail(request.email())) {
			throw new EmailAlreadyExistsException("이미 사용중인 이메일입니다.");
		}

		String passwordHash = passwordEncoder.encode(request.password());

		LocalDate birthDate = LocalDate.parse(request.birthDate());

		User user = new User(
			request.email(),
			passwordHash,
			request.tel(),
			request.name(),
			request.gender(),
			birthDate,
			request.location(),
			request.education(),
			request.nickname()
		);

		user = userRepository.save(user);
		return user.getNickname();
	}

	@Transactional(readOnly = true)
	public LoginResponse login(LoginRequest request) {
		User user = userRepository.findByEmail(request.email())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

		if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getEmail());
		String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

		redisTemplate.opsForValue().set("refreshToken:" + user.getId(), refreshToken, Duration.ofDays(7));

		return new LoginResponse(user.getId(), user.getEmail(), user.getTel(), accessToken, refreshToken);
	}

	@Transactional(readOnly = true)
	public String refreshAccessToken(String refreshToken) {
		if (!jwtTokenProvider.validateToken(refreshToken)) {
			throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
		}

		Long userId = jwtTokenProvider.getUserId(refreshToken);

		String storedToken = redisTemplate.opsForValue().get("refreshToken:" + userId);
		if (storedToken == null || !storedToken.equals(refreshToken)) {
			throw new IllegalArgumentException("리프레시 토큰이 만료되었거나 일치하지 않습니다.");
		}

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

		// 새 accessToken 발급
		return jwtTokenProvider.generateAccessToken(user.getId(), user.getEmail());
	}

}
