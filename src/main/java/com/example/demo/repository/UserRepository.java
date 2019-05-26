package com.example.demo.repository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.Broker;
import com.example.demo.entity.User;
import com.example.demo.entity.response.UserSearchResponse;
import com.example.demo.repository.extension.UserRepositoryExtension;


public interface UserRepository extends JpaRepository<User, Long> {
  
	
	List<User> findAll();
	Optional<User> findByEmail(String email);
	User findByBroker(Broker broker);
	//User save(User u);
	
	@Transactional
	@Modifying
	@Query(value = "insert into users values(0,?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)", nativeQuery = true)
	void addUser(Boolean active, String address, String dateOfBirth, String description, String email, Boolean gender,
			String nameBroker, Boolean nonDel, Boolean nonBlock, String password, String phoneNumber, Long id);
	
	
	
	
	
	
//	@Transactional
//	@Modifying
//	@Query(value = "insert into users values('' ,?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)", nativeQuery = true)
//	void addUser(Boolean active, String address, String dateOfBirth, String description, String email, Boolean gender,
//			String nameBroker, Boolean nonDel, Boolean nonBlock, String password, String phoneNumber, Long roleId);
	
}
