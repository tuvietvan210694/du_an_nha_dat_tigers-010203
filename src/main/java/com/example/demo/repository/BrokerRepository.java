package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.Broker;
import com.example.demo.entity.response.UserSearchResponse;
import com.example.demo.repository.extension.UserRepositoryExtension;


@Repository
@Transactional
public interface BrokerRepository extends JpaRepository<Broker, Long> {

	//List<Broker> findByUserEmail(String email);
    //Optional<Broker> findUserByDescription(String decsription);

	Broker findByUserEmail(String email);
	List<Broker> findByUserEmailContainingOrUserNameUserContainingOrUserPhoneNumberContainingOrUserAddressContaining(String email, String nameUser, String phoneNumber, String address);
	//List<UserSearchResponse> seekBroker(QueryParam<UserSearchResponse> page);
	Page<Broker> findByUserNonDel(boolean b, Pageable firstPageWithTwoElements);
	List<Broker> findByUserNonDel(boolean b);
	
}
