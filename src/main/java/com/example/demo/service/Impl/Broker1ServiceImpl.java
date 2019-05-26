//package com.example.demo.service.Impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.domain.QueryParam;
//import com.example.demo.entity.response.UserSearchResponse;
//import com.example.demo.repository.Broker1Repository;
//import com.example.demo.service.Broker1Service;
//
//@Service
//public class Broker1ServiceImpl implements Broker1Service {
//    
//	@Autowired
//	private Broker1Repository broker1Repository;
//	
//	@Override
//	public List<UserSearchResponse> seek(QueryParam<UserSearchResponse> page) {
//		List<UserSearchResponse> list = broker1Repository.seek(page);
//		return list;
//	}
//
//}
