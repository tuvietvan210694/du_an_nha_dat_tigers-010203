package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

}
