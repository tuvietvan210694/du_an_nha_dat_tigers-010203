package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Product;
import com.example.demo.entity.response.ProductPublicResponse;

public interface ProductService {

	List<Product> findByRequestStatus(String pending);

	Product findById(long id);

	List<Product> findAll();
	

	List<Product> listProductByFilter(String search);

	List<Product> findByRequestStatus(String pending, Pageable phantrang);

	List<Product> findByRequestStatusAndProductStatus(String approved, boolean b, Pageable phantrang);

	ProductPublicResponse convertToProductResponsePublic(Product product);

	List<Product> findByRequestStatusAndProductStatus(String approved, boolean b);

	List<Product> findAll(Pageable phantrang);

	List<Product> findByRequestStatusAndNonDel(String pending, boolean b, Pageable phantrang);

	List<Product> findByRequestStatusAndNonDel(String pending, boolean b);

	List<Product> findByNonDel(boolean b);

	List<Product> findByNonDel(boolean b, Pageable phantrang);
	
}
