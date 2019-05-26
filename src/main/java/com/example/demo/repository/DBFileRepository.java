package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {

}
