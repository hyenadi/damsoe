package com.hyeidle.damsoe.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyeidle.damsoe.domain.entity.User;
import com.hyeidle.damsoe.domain.repository.UserRepository;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
