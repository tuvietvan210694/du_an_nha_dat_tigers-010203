package com.example.demo.repository.extension;

import java.util.List;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.UserSearchResponse;

public interface UserRepositoryExtension {
	List<UserSearchResponse> seek(QueryParam<UserSearchResponse> page);

}
