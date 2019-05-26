package com.example.demo.service;

import java.text.ParseException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.domain.QueryParam;
import com.example.demo.entity.Broker;
import com.example.demo.entity.response.BrokerResponse;
import com.example.demo.entity.response.UserSearchResponse;

public interface BrokerService {
    List<Broker> findAll();

	Broker getBrokerById(long id);
	BrokerResponse convertToResponse(Broker bro) throws ParseException;
	List<BrokerResponse> listBrokerByFilter(String search) throws ParseException;

	List<Broker> findAll(Pageable firstPageWithTwoElements);

	List<Broker> findByNonDel(boolean b, Pageable firstPageWithTwoElements);

	List<Broker> findByNonDel(boolean b);

	//List<UserSearchResponse> seek(QueryParam<UserSearchResponse> page);
}
