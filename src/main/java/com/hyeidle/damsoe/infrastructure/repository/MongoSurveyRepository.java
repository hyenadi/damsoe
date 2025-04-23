package com.hyeidle.damsoe.infrastructure.repository;

import com.hyeidle.damsoe.domain.document.Survey;
import com.hyeidle.damsoe.domain.repository.SurveyRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoSurveyRepository extends MongoRepository<Survey, String>, SurveyRepository {

	@Override
	Optional<Survey> findByUserId(Long userId);
	@Override
	boolean existsByUserId(Long userId);
	@Override
	Survey save(Survey survey);

}
