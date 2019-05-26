package com.example.demo.repository.extension;

import java.util.List;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.entity.response.PublicResponse;

public interface PublicRepositoryExtension {
	List<PublicResponse> getPublic(QueryParam<PublicResponse> page);

}
