package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;

import com.example.demo.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService{

	@Override
	public boolean checkDuplicateMatchingPassword(String newPassword, String newMatchingPassword) {
		// TODO Auto-generated method stub
		return newPassword.equals(newMatchingPassword);
	}

}
