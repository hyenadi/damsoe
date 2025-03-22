package com.hyeidle.damsoe.domain.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자를 나타낸다.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(name = "tel", nullable = false)
	private String tel;

	@JdbcTypeCode(SqlTypes.VECTOR)
	@Column(name = "persona_vector", columnDefinition = "vector")
	@Setter
	private float[] personaVector;

	public User(String email, String passwordHash) {
		this.email = email;
		this.passwordHash = passwordHash;
	}

	public User(String email, String passwordHash, String tel) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.tel = tel;
	}

}
