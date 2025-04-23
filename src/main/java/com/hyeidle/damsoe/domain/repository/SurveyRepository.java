package com.hyeidle.damsoe.domain.repository;

import com.hyeidle.damsoe.domain.document.Survey;

import java.util.Optional;

import aj.org.objectweb.asm.commons.Remapper;

public interface SurveyRepository {
	Optional<Survey> findByUserId(Long userId);
	boolean existsByUserId(Long userId);
	Survey save(Survey survey);
}
