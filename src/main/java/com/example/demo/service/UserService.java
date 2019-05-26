package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.User;
import com.example.demo.entity.response.UserSearchResponse;

public interface UserService {
	User findByEmail(String email);

	Object getUsers(Pageable page);

	//List<UserSearchResponse> seekBroker(QueryParam<UserSearchResponse> page);
    
}
