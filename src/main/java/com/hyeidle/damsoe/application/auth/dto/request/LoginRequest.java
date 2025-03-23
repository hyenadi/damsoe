package com.hyeidle.damsoe.application.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "올바르지 않은 이메일 형식입니다.")
	@NotBlank String email,

	@NotBlank(message = "패스워드는 필수입니다.")
	@NotBlank String password
) {
}
