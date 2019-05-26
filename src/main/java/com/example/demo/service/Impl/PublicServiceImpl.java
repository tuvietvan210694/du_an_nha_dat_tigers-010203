package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.entity.response.PublicResponse;
import com.example.demo.repository.PublicRepository;
import com.example.demo.service.PublicService;

@Service
public class PublicServiceImpl implements PublicService {
    
	@Autowired
	private PublicRepository publicRepository;

	@Override
	public List<PublicResponse> getPublic(QueryParam<PublicResponse> page) {
		// TODO Auto-generated method stub
		List<PublicResponse> data = publicRepository.getPublic(page);
		return data;
	}
	
}
