package com.hyeidle.damsoe.application.survey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SurveyRequest(

	@NotEmpty(message = "성격 키워드는 최소 1개 이상이어야 합니다.")
	List<@NotBlank(message = "성격 키워드 항목은 비워둘 수 없습니다.") String> keywords,

	@NotBlank(message = "MBTI는 필수입니다.")
	String mbti,

	@NotBlank(message = "말투 스타일은 필수입니다.")
	String speakingStyle,

	@NotEmpty(message = "관심 분야는 최소 1개 이상이어야 합니다.")
	List<@NotBlank(message = "관심 분야 항목은 비워둘 수 없습니다.") String> hobbies,

	@NotEmpty(message = "연애 스타일은 최소 1개 이상이어야 합니다.")
	List<@NotBlank(message = "연애 스타일 항목은 비워둘 수 없습니다.") String> loveStyle,

	@NotEmpty(message = "민감 주제는 최소 1개 이상이어야 합니다.")
	List<@NotBlank(message = "민감 주제 항목은 비워둘 수 없습니다.") String> sensitiveTopics,

	@NotBlank(message = "감정 표현 방식은 필수입니다.")
	String emotionStyle,

	@NotBlank(message = "대화 속도는 필수입니다.")
	String speechSpeed

) {}
