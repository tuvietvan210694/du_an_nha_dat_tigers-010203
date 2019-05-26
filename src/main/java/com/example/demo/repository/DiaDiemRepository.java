package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Broker;
import com.example.demo.entity.City;
import com.example.demo.entity.District;

@Repository
@Transactional
public interface DiaDiemRepository extends JpaRepository<City, Long> {

}
