package com.hyeidle.damsoe.application.survey;

import org.springframework.stereotype.Service;

import com.hyeidle.damsoe.application.survey.dto.request.SurveyRequest;
import com.hyeidle.damsoe.domain.document.Survey;
import com.hyeidle.damsoe.domain.repository.SurveyRepository;

@Service
public class SurveyService {

	private final SurveyRepository surveyRepository;

	public SurveyService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}

	public void saveSurvey(Long userId, SurveyRequest request) {
		Survey.SurveyData data = new Survey.SurveyData(
			request.keywords(),
			request.mbti(),
			request.speakingStyle(),
			request.hobbies(),
			request.loveStyle(),
			request.sensitiveTopics(),
			request.emotionStyle(),
			request.speechSpeed()
		);

		Survey survey = Survey.builder()
			.userId(userId)
			.data(data)
			.build();

		surveyRepository.save(survey);
	}

	public Survey getSurveyDataByUserId(Long userId) {
		return surveyRepository.findByUserId(userId)
			.orElse(null);
	}
}
