package com.example.demo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Ward;

@Repository
@Transactional
public interface WardRepository extends JpaRepository<Ward, Long> {

	Optional<Ward> findByMaWard(String productLocation);

}
