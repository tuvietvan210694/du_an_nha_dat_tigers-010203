package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CustomerNotAuthority;

public interface CustomerNotAuthorityRepository extends JpaRepository<CustomerNotAuthority, Long>{


	Page<CustomerNotAuthority> findByNonDel(boolean b, Pageable firstPageWithTwoElements);

	List<CustomerNotAuthority> findByNonDel(boolean b);

	Optional<CustomerNotAuthority> findByIdCard(String search);

	List<CustomerNotAuthority> findByIdCardContaining(String search);

//	List<CustomerNotAuthority> findByIdCardContaining(String search);


}
