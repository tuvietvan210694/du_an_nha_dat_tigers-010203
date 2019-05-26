package com.example.demo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.District;

@Repository
@Transactional
public interface DistrictRepository extends JpaRepository<District, Long> {

	Optional<District> findByNameDistrict(String name);

	Optional<District> findByMaDistrict(String ma);

}