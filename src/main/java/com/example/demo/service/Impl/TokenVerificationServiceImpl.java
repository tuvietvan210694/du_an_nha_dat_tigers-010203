package com.example.demo.service.Impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TokenVerifition;
import com.example.demo.repository.TokenVerifitionRepository;
import com.example.demo.service.TokenVerificationService;


@Service
public class TokenVerificationServiceImpl implements TokenVerificationService {
    
	@Autowired
	TokenVerifitionRepository tokenVerifitionRepository;
	
	@Override
	public void addTokenFunction(Date expireDate, String registCode, Long user_id) {
		// TODO Auto-generated method stub
		tokenVerifitionRepository.addTokenFunction(expireDate, registCode, user_id);
		
	}

	@Override
	public TokenVerifition findTokenByTokenCode(String token) {
		// TODO Auto-generated method stub
		Optional<TokenVerifition> optionalToken = tokenVerifitionRepository.findByTokenCode(token);
		if (!optionalToken.isPresent()) {
			return null;
		}
		return optionalToken.get();
	}

}
