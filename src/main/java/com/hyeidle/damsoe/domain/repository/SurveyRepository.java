package com.hyeidle.damsoe.domain.repository;

import com.hyeidle.damsoe.domain.document.Survey;

import java.util.Optional;

public interface SurveyRepository {
	Survey save(Survey survey);
	Optional<Survey> findByUserId(Long userId);
	boolean existsByUserId(Long userId);
}
