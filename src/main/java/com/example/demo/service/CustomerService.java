package com.example.demo.service;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.response.CustomerResponse;


public interface CustomerService {
	
	List<Customer> findAll();

	CustomerResponse convertToCusRes(Customer customer);

	Customer getCustomerById(long id);

	List<CustomerResponse> listCustomerByFilter(String search);

	List<Customer> listCustomerByFilter1(String search);

	List<Customer> findAll(Pageable firstPageWithTwoElements);
	
}
