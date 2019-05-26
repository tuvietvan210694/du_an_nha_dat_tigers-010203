package com.example.demo.repository;
import com.example.demo.entity.BlockUser;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BlockUserRepository extends CrudRepository<BlockUser, Long> {

}
