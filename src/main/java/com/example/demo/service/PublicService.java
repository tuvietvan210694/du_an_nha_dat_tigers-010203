package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.entity.response.PublicResponse;

public interface PublicService {

	List<PublicResponse> getPublic(QueryParam<PublicResponse> page);

}
