package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CatalogDetail;

public interface CatalogDetailRepository extends JpaRepository<CatalogDetail, Long>{

	List<CatalogDetail> findByCatalogId(Long id);

}
