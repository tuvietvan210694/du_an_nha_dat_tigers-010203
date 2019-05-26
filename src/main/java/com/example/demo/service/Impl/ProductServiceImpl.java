package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.entity.response.ProductPublicResponse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findByRequestStatus(String pending) {
		
		List<Product> listProduct = productRepository.findByRequestStatus(pending);
		if(listProduct == null) {
			return null;
		}
		return listProduct;
	}

	@Override
	public Product findById(long id) {
		Optional<Product> product = productRepository.findById(id);
		if(!product.isPresent()) {
			return null;
		}
		return product.get();
	}

	@Override
	public List<Product> findAll() {
		
		List<Product> listProduct = productRepository.findAll();
		if(listProduct == null) {
			return null;
		}
		return listProduct;
	}

	@Override
	public List<Product> listProductByFilter(String search) {
		
		List<Product> listProduct = productRepository.findByProductNameContainingOrRequestStatusContaining(search, search);
		if(listProduct == null) {
			return null;
		}
		return listProduct;
	}

	@Override
	public List<Product> findByRequestStatus(String pending, Pageable phantrang) {
		
		Page<Product> p = productRepository.findByRequestStatus(pending, phantrang);
		List<Product> list = p.getContent();
		return list;
	}

	@Override
	public List<Product> findByRequestStatusAndProductStatus(String approved, boolean b, Pageable phantrang) {
		
		Page<Product> p = productRepository.findByRequestStatusAndProductStatus(approved, b, phantrang);
		List<Product> list = p.getContent();
		return list;
	}

	@Override
	public ProductPublicResponse convertToProductResponsePublic(Product product) {
		
		ProductPublicResponse pro = new ProductPublicResponse();
		pro.setId(product.getId());
		pro.setProductName(product.getProductName());
		pro.setProductLocation(product.getProductLocation());
		pro.setProductCost(product.getProductCost());
		pro.setProductAcreage(product.getProductAcreage());
		pro.setRequestStatus(product.getRequestStatus());
		pro.setFileId(product.getdBFile().getId());
		String linkAnh = "https://tiger010203.herokuapp.com/downloadFile" + "/" + product.getdBFile().getId();
		pro.setLinkAnh(linkAnh);
		return pro;
	}

	@Override
	public List<Product> findByRequestStatusAndProductStatus(String approved, boolean b) {
		List<Product> list = productRepository.findByRequestStatusAndProductStatus(approved, b);
		if((list == null) || (list.size() == 0)) {
			return null;
		}
		return list;
	}

	@Override
	public List<Product> findAll(Pageable phantrang) {
		// TODO Auto-generated method stub
		Page<Product> p = productRepository.findAll(phantrang);
		List<Product> list = p.getContent();
		return list;
	}

	@Override
	public List<Product> findByRequestStatusAndNonDel(String pending, boolean b, Pageable phantrang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByRequestStatusAndNonDel(String pending, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByNonDel(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByNonDel(boolean b, Pageable phantrang) {
		// TODO Auto-generated method stub
		return null;
	}
}
