package com.hyeidle.damsoe.application.auth.dto.response;

public record LoginResponse(
	Long userId,
	String email,
	String tel,
	String accessToken,
	String refreshToken
) {
}
