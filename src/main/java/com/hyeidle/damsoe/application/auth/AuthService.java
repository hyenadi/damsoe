package com.hyeidle.damsoe.application.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyeidle.damsoe.application.auth.dto.request.LoginRequest;
import com.hyeidle.damsoe.application.auth.dto.request.RegisterRequest;
import com.hyeidle.damsoe.application.auth.dto.response.LoginResponse;
import com.hyeidle.damsoe.application.auth.exception.EmailAlreadyExistsException;
import com.hyeidle.damsoe.domain.entity.User;
import com.hyeidle.damsoe.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public String registerUser(RegisterRequest request) {
		if (userRepository.existsByEmail(request.email())) {
			throw new EmailAlreadyExistsException("이미 사용중인 이메일입니다.");
		}
		String passwordHash = passwordEncoder.encode(request.password());
		User user = new User(request.email(), passwordHash, request.tel());
		user = userRepository.save(user);
		return user.getId().toString();
	}

	@Transactional(readOnly = true)
	public LoginResponse login(LoginRequest request) {
		User user = userRepository.findByEmail(request.email())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

		if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return new LoginResponse(user.getId(), user.getEmail(), user.getTel());
	}

}
