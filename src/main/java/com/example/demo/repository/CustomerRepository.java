package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{

	List<Customer> findByUserEmailContainingOrUserNameUserContainingOrUserPhoneNumberContainingOrUserAddressContaining(
			String email, String nameUser, String phoneNumber, String address);

}
