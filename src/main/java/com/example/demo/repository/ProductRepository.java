package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Broker;
import com.example.demo.entity.CatalogDetail;
import com.example.demo.entity.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByRequestStatus(String pending);

//	List<Product> findByProductNameContainingOrRequestStatusOrProductStatus(String search, String search2,
//			String search3);

	List<Product> findByProductNameContainingOrRequestStatusContaining(String search, String search2);

	//List<Product> findByBroker(Broker broker);

	Page<Product> findByRequestStatus(String pending, Pageable phantrang);

	Page<Product> findByRequestStatusAndProductStatus(String approved, boolean b, Pageable phantrang);

	List<Product> findByCustomerNotAuthorityIsNull();

	List<Product> findByCatalogDetailAndZipCode(CatalogDetail catalogDetail, String zipCode);

	List<Product> findByCatalogDetailAndZipCodeAndRequestStatusAndProductStatus(CatalogDetail catalogDetail,
			String zipcode, String approved, boolean b);

	List<Product> findByBrokerUserEmail(String email);

	List<Product> findByBrokerUserEmailAndRequestStatus(String email, String approved);

	List<Product> findByRequestStatusAndProductStatus(String approved, boolean b);

	List<Product> findByBrokerUserEmailAndRequestStatus(String email, String approved, Pageable phantrang);

	Optional<Product> findByAdminAndId(Admin admin, long idProduct);

	List<Product> findByBrokerUserEmailAndNonDelAndRequestStatus(String email, boolean b, String pending,
			Pageable phantrang);

	List<Product> findByBrokerUserEmailAndNonDelAndRequestStatus(String email, boolean b, String approved);

	List<Product> findByBroker(Broker broker);

	List<Product> findByOwnerBrokerAndNonDelAndRequestStatus(Broker broker, boolean b, String pending,
			Pageable phantrang);

	List<Product> findByOwnerBrokerAndNonDelAndRequestStatus(Broker broker, boolean b, String approved);

	List<Product> findByRequestStatusAndNonDel(String pending, boolean b, Pageable phantrang);

	List<Product> findByRequestStatusAndNonDel(String pending, boolean b);

	List<Product> findByNonDel(boolean b);

	List<Product> findByNonDel(boolean b, Pageable phantrang);

	List<Product> findByAdminUserEmail(String email);

	List<Product> findByBrokerUserEmailAndProductStatusAndNonDel(String email, boolean b, boolean c);

	List<Product> findByBrokerUserEmailAndProductStatusAndNonDelAndRequestStatus(String email, boolean b, boolean c,
			String approved);

	List<Product> findByAdminUserEmailAndNonDel(String email, boolean b);

	List<Product> findByOwnerBrokerUserEmailAndNonDelAndRequestStatus(String email, boolean b, String approved);

	//List<Product> findByBroker(Broker broker, Pageable phantrang);

	//List<Product> findByBrokerAndRequestStatus(Broker broker, String pending, Pageable phantrang);
	

}
