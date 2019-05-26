package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.response.CustomerResponse;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public List<Customer> findAll() {
		List<Customer> listCustomer = customerRepository.findAll();
		if(listCustomer.size() == 0) {
			return null;
		}
		// TODO Auto-generated method stub
		return listCustomer;
	}

	@Override
	public CustomerResponse convertToCusRes(Customer customer) {
		
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setId(customer.getId());
		customerResponse.setActive(customer.getUser().getActive());
		customerResponse.setAddress(customer.getUser().getAddress());
		customerResponse.setDateOfBirth(customer.getUser().getDayOfBirth().toString());
		customerResponse.setEmail(customer.getUser().getEmail());
		customerResponse.setGender(customer.getUser().getGender());
		customerResponse.setNameBroker(customer.getUser().getNameUser());
		customerResponse.setNonDel(customer.getUser().getNonDel());
		customerResponse.setPhoneNumber(customer.getUser().getPhoneNumber());
		
		return customerResponse;
	}

	@Override
	public Customer getCustomerById(long id) {
		Customer cus = customerRepository.getOne(id);
		if(cus == null) {
			return null;
		}
		// TODO Auto-generated method stub
		return cus;
	}

	@Override
	public List<CustomerResponse> listCustomerByFilter(String search) {
		List<Customer> listCustomer = customerRepository.findByUserEmailContainingOrUserNameUserContainingOrUserPhoneNumberContainingOrUserAddressContaining(search, search, search, search);
		// TODO Auto-generated method stub
		List<CustomerResponse> listCusRes = new ArrayList<>();
		CustomerResponse cusRes = null;
		for(Customer cus: listCustomer) {
			cusRes = customerService.convertToCusRes(cus);
			listCusRes.add(cusRes);
		}
		return listCusRes;
	}

	@Override
	public List<Customer> listCustomerByFilter1(String search) {
		// TODO Auto-generated method stub
		List<Customer> listCustomer = customerRepository.findByUserEmailContainingOrUserNameUserContainingOrUserPhoneNumberContainingOrUserAddressContaining(search, search, search, search);
		return listCustomer;
	}

	@Override
	public List<Customer> findAll(Pageable firstPageWithTwoElements) {
		// TODO Auto-generated method stub
		Page<Customer> p = customerRepository.findAll(firstPageWithTwoElements);
		List<Customer> list = p.getContent();
		return list;
	}
	
}
