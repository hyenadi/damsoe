package com.hyeidle.damsoe.domain.entity;

import java.time.LocalDate;

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

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "education", nullable = false)
	private String education;

	@JdbcTypeCode(SqlTypes.VECTOR)
	@Column(name = "persona_vector", columnDefinition = "vector")
	@Setter
	private float[] personaVector;

	public User(String email, String passwordHash, String tel, String name, String gender, LocalDate birthDate,
		String location, String education) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.tel = tel;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.location = location;
		this.education = education;
	}
}
