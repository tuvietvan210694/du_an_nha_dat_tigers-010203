package com.example.demo.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TokenVerifition;


public interface TokenVerifitionRepository extends JpaRepository<TokenVerifition, Long> {
	@Transactional
	@Modifying
	@Query(value = "insert into token_verifition values(0 ,?1, ?2, ?3)", nativeQuery = true)
	int addTokenFunction(Date expireDate, String registCode, Long user_id);

	Optional<TokenVerifition> findByTokenCode(String token);

}
