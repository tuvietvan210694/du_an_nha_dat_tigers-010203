package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.DistrictResponse;

public interface District1Service {

	List<DistrictResponse> getDistrict1(QueryParam<DistrictResponse> page);

}
