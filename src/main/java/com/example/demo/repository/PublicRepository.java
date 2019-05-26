package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Product;
import com.example.demo.repository.extension.PublicRepositoryExtension;

public interface PublicRepository extends JpaRepository<Product, Long>, PublicRepositoryExtension {

	//List<DistrictResponse> getPublic(QueryParam<PublicResponse> page);

}
