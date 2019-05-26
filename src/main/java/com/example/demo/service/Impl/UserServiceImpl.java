package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.User;
import com.example.demo.entity.response.UserSearchResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public User findByEmail(String email) {
		
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}
	@Override
	public Object getUsers(Pageable page) {
		
		System.out.println("page.getOffset(): " + page.getOffset());
		System.out.println("page.getPageNumber(): " + page.getPageNumber());
		System.out.println("page.getPageSize(): " + page.getPageSize());;
		System.out.println("page.getSort(): " + page.getSort());
		Page<User> u = userRepository.findAll(page);
		List<User> lu = u.getContent();
		
		return lu;
	}
//	@Override
//	public List<UserSearchResponse> seekBroker(QueryParam<UserSearchResponse> page) {
//		List<UserSearchResponse> list = userRepository.seekBroker(page);
//		return list;
//	}

	

}
