package com.example.demo.service.Impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerNotAuthority;
import com.example.demo.entity.response.CustomerNotAuthorityResponse;
import com.example.demo.repository.CustomerNotAuthorityRepository;
import com.example.demo.service.CustomerNotAuthorityService;

@Service
public class CustomerNotAuthorityServiceImpl implements CustomerNotAuthorityService {
	
    @Autowired
    private CustomerNotAuthorityRepository customerNotAuthorityRepository;
	@Override
	public CustomerNotAuthority getCustomerById(long id) {
		
		CustomerNotAuthority cus = customerNotAuthorityRepository.getOne(id);
		if(cus == null) {
			return null;
		}
		return cus;
	}
	@Override
	public CustomerNotAuthorityResponse convertToCusRes(CustomerNotAuthority customer) throws ParseException{
		
		String date;
		CustomerNotAuthorityResponse cus = new CustomerNotAuthorityResponse();
		cus.setId(customer.getId());
		cus.setNameCustomer(customer.getNameCustomer());
		cus.setPhoneNumber(customer.getPhoneNumber());
		DateFormat format1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		date = format1.format(customer.getDayOfBirth());
		cus.setDayOfBirth(date);
		cus.setDescription(customer.getDescription());
		cus.setIdCard(customer.getIdCard());
		cus.setNonDel(customer.getNonDel());
		return cus;
	}
//	@Override
//	public List<CustomerNotAuthorityResponse> findAll(Pageable firstPageWithTwoElements) throws ParseException {
//		
//		Page<CustomerNotAuthority> listCustomer = customerNotAuthorityRepository.findAll(firstPageWithTwoElements);
//		if(listCustomer == null) {
//			return null;
//		}
//		List<CustomerNotAuthority> listCus = listCustomer.getContent();
//		if(listCus == null) {
//			return null;
//		}
//		List<CustomerNotAuthorityResponse> list= new ArrayList<>();
//		CustomerNotAuthorityResponse cusRes;
//		for(CustomerNotAuthority cus: listCus) {
//			cusRes = convertToCusRes(cus);
//			list.add(cusRes);			
//		}
//		if(list.size() == 0) {
//			return null;
//		}
//		
//		return list;
//	}
	@Override
	public List<CustomerNotAuthority> findAll(Pageable firstPageWithTwoElements) throws ParseException {
		Page<CustomerNotAuthority> listCustomer = customerNotAuthorityRepository.findAll(firstPageWithTwoElements);
		List<CustomerNotAuthority> list = listCustomer.getContent();
		return list;
	}
	@Override
	public List<CustomerNotAuthority> findByNonDel(boolean b, Pageable firstPageWithTwoElements) {
		Page<CustomerNotAuthority> listCustomer = customerNotAuthorityRepository.findByNonDel(true, firstPageWithTwoElements);
		List<CustomerNotAuthority> list = listCustomer.getContent();
		return list;
		
	}
	

}
