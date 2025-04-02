package com.hyeidle.damsoe.infrastructure.repository;

import com.hyeidle.damsoe.domain.document.Survey;
import com.hyeidle.damsoe.domain.repository.SurveyRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// Spring Data MongoDB와 도메인 인터페이스를 동시에 상속
public interface MongoSurveyRepository extends MongoRepository<Survey, Long>, SurveyRepository {

	@Override
	Optional<Survey> findByUserId(Long userId);
	@Override
	boolean existsByUserId(Long userId);
}
