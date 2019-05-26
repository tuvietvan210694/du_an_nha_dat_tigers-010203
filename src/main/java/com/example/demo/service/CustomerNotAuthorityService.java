package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.entity.CustomerNotAuthority;
import com.example.demo.entity.response.CustomerNotAuthorityResponse;

public interface CustomerNotAuthorityService {

	CustomerNotAuthority getCustomerById(long id);

	CustomerNotAuthorityResponse convertToCusRes(CustomerNotAuthority customer) throws ParseException;

	List<CustomerNotAuthority> findAll(Pageable firstPageWithTwoElements) throws ParseException;

	List<CustomerNotAuthority> findByNonDel(boolean b, Pageable firstPageWithTwoElements);

	

}
