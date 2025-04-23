package com.hyeidle.damsoe.application.survey;

import com.hyeidle.damsoe.application.survey.dto.request.SurveyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
		@AuthenticationPrincipal @NonNull Long userId,
		@Valid @RequestBody @NonNull SurveyRequest request
	) {
		surveyService.saveSurvey(userId, request);
		return ResponseEntity.ok().body("{\"message\": \"설문이 성공적으로 저장되었습니다.\"}");
	}

	@GetMapping("/api/survey")
	@Operation(
		summary = "설문 조회",
		description = "JWT userId를 기반으로 MongoDB에서 설문을 조회합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "설문 조회 성공",
				content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(value = """
					{
					  "keywords": ["진지함", "유머"],
					  "mbti": "INFP",
					  "speakingStyle": "유머러스한",
					  "hobbies": ["독서", "산책"],
					  "loveStyle": ["직진형"],
					  "sensitiveTopics": ["정치", "종교"],
					  "emotionStyle": "직접적",
					  "speechSpeed": "느림"
					}
				""")
				)
			),
			@ApiResponse(
				responseCode = "404",
				description = "해당 사용자의 설문 데이터가 존재하지 않습니다.",
				content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(value = "{\"message\": \"설문 정보가 없습니다.\"}")
				)
			)
		}
	)
	public ResponseEntity<?> getSurvey(@AuthenticationPrincipal Long userId) {
		var data = surveyService.getSurveyDataByUserId(userId);
		if (data == null) {
			return ResponseEntity.status(404).body("{\"message\": \"설문 정보가 없습니다.\"}");
		}
		return ResponseEntity.ok(data);
	}
}
