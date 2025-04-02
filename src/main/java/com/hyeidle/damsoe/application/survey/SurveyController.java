package com.hyeidle.damsoe.application.survey;

import com.hyeidle.damsoe.application.survey.dto.request.SurveyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyController {

	private final SurveyService surveyService;

	public SurveyController(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@PostMapping("/api/survey")
	@Operation(
		summary = "설문 저장",
		description = "JWT userId를 기반으로 MongoDB에 설문을 저장합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "설문 저장 성공",
				content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(value = "{\"message\": \"설문이 성공적으로 저장되었습니다.\"}")
				)
			)
		}
	)
	public ResponseEntity<?> saveSurvey(
		@AuthenticationPrincipal Long userId,
		@Valid @RequestBody SurveyRequest request
	) {
		surveyService.saveSurvey(userId, request);
		return ResponseEntity.ok().body("{\"message\": \"설문이 성공적으로 저장되었습니다.\"}");
	}
}
