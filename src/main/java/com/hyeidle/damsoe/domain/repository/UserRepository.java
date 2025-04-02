package com.hyeidle.damsoe.domain.repository;

import java.util.Optional;

import com.hyeidle.damsoe.domain.entity.User;

public interface UserRepository {
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	User save(User user);
	Optional<User> findById(Long userId);
}
