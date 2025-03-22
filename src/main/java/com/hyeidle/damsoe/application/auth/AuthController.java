package com.hyeidle.damsoe.application.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyeidle.damsoe.application.auth.dto.request.LoginRequest;
import com.hyeidle.damsoe.application.auth.dto.request.RegisterRequest;
import com.hyeidle.damsoe.application.auth.dto.response.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * 사용자 인증 관련 API를 제공한다.
 */
@RestController
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * 사용자 가입을 처리한다.
	 *
	 * @param request 가입 요청 데이터
	 * @return 등록된 사용자 ID
	 */
	@PostMapping("/api/auth/register")
	@Operation(summary = "회원 가입", description = "새로운 사용자를 시스템에 등록한다.")
	@ApiResponse(
		responseCode = "200",
		description = "가입 성공, 사용자 ID 반환",
		content = @Content(
			mediaType = "application/json",
			examples = @ExampleObject(
				name = "회원가입 성공 예시",
				value = "userId"
			)
		)
	)
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
		String userId = authService.registerUser(request);
		return ResponseEntity.ok(userId);
	}

	/**
	 * 사용자 로그인을 처리한다.
	 *
	 * @param request 로그인 요청 데이터
	 * @return 등록된 사용자 ID
	 */
	@PostMapping("/api/auth/login")
	@Operation(summary = "로그인", description = "이메일과 비밀번호로 사용자 로그인 처리")
	@ApiResponse(
		responseCode = "200",
		description = "로그인 성공, 사용자 정보 반환",
		content = @Content(
			mediaType = "application/json",
			examples = @ExampleObject(
				name = "로그인 성공 예시",
				value = """
					{"userId": "userId","email": "test@example.com"}
					"""
			)
		)
	)
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		LoginResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}

}
