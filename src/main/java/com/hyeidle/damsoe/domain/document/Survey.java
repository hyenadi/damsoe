package com.hyeidle.damsoe.domain.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "surveys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {

	@Id
	private Long userId;

	// 전체 설문 결과를 하나의 객체로 묶음
	private SurveyData data;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SurveyData {
		private List<String> keywords;         // 성격 키워드
		private String mbti;                  // MBTI
		private String speakingStyle;         // 말투 스타일
		private List<String> hobbies;         // 관심 분야
		private List<String> loveStyle;       // 연애 스타일
		private List<String> sensitiveTopics; // 민감 주제
		private String emotionStyle;          // 감정 표현 방식
		private String speechSpeed;           // 대화 속도
	}
}
