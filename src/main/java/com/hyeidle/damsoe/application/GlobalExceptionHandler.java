package com.hyeidle.damsoe.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hyeidle.damsoe.application.auth.exception.EmailAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

	// 이미 존재하는 이메일 예외 (409)
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.CONFLICT);
	}

	// 유효성 검증 실패 (400)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.BAD_REQUEST);
	}

	// 잘못된 이메일/비밀번호 등 입력 오류 (401)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	// 기타 서버 오류 (500)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		return new ResponseEntity<>(new ErrorResponse("Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
