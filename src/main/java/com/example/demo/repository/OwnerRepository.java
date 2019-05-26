package com.example.demo.repository;
import java.security.Principal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long>{

	List<Owner> findByBrokerUserEmailAndTrangThai(String email, boolean b);

	//List<Owner> findByBrokerUserEmail(String email, Principal p);

	List<Owner> findByBrokerUserEmail(String email);

	List<Owner> findByIdCardContainingOrOwnerNameContainingOrPhoneNumber(String search, String search2, String search3);

	
}
