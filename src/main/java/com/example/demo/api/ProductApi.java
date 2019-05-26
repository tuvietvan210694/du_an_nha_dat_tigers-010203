package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Catalog;
import com.example.demo.entity.CatalogDetail;
import com.example.demo.repository.CatalogDetailRepository;
import com.example.demo.repository.CatalogRepository;

@RestController
public class ProductApi {
	
	@Autowired
	private CatalogRepository catalogRepository;
	@Autowired
	private CatalogDetailRepository catalogDetailRepository;
	
	@GetMapping(path = "/listCatalog")
	public ResponseEntity<Object> listCatalog() {
		List<Catalog> listCatalog = catalogRepository.findAll();
		if(listCatalog == null || listCatalog.size() == 0) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listCatalog, HttpStatus.OK);
	}
	
	@GetMapping(path = "/catalog/{id}")
	public ResponseEntity<Object> listCatalogDetail(@PathVariable("id") Long id) {
		List<CatalogDetail> listCatalogDetail = catalogDetailRepository.findByCatalogId(id);
		if(listCatalogDetail == null || listCatalogDetail.size() == 0) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listCatalogDetail, HttpStatus.OK);
	}


}
