package com.example.demo.service;

import java.util.Date;

import com.example.demo.entity.TokenVerifition;

public interface TokenVerificationService {
    
	void addTokenFunction(Date expireDate, String registCode, Long user_id);

	TokenVerifition findTokenByTokenCode(String token);
}
