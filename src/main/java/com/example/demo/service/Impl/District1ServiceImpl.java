package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.repository.District1Repository;
import com.example.demo.service.District1Service;

@Service
public class District1ServiceImpl implements District1Service {
    
	@Autowired
	private District1Repository district1Repository;
	@Override
	public List<DistrictResponse> getDistrict1(QueryParam<DistrictResponse> page) {
		// TODO Auto-generated method stub
		List<DistrictResponse> data = district1Repository.getDistrict1(page);
		return data;
	}

}
