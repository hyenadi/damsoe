package com.hyeidle.damsoe.application.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "올바르지 않은 이메일 형식입니다.")
	String email,

	@NotBlank(message = "패스워드는 필수입니다.")
	@Size(min = 8, message = "패스워드는 8글자 이상이어야 합니다.")
	String password,

	@NotBlank(message = "전화번호는 필수입니다.")
	@NotBlank String tel
) {
}
