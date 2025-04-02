package com.hyeidle.damsoe.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyeidle.damsoe.domain.entity.User;
import com.hyeidle.damsoe.domain.repository.UserRepository;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
}
