package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.City;
import com.example.demo.entity.dto.DiaDiemDTO;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

	Optional<City> findByMaCity(String name);
	
//	@Modifying
//	@Query(value= "delete from role_group where group_id=?1",nativeQuery=true)
//	Integer deleteByGroupId(Long groupId);
    
//	@Modifying
//	@Query(value= "delete from role_group where group_id=?1",nativeQuery=true)
	@Modifying
	@Query(value= "select * from city where ma_city = '48' ",nativeQuery=true)
	List<City> findListCity();
    
	@Modifying
	@Query(value= "select c from City c where c.maCity = '79' ")
	List<City> findListCity2();
    
	@Modifying
	@Query(value= "select c from City c where c.maCity = ?1 ")
	List<City> findListCity3(String string);
    
	@Modifying
	@Query(value= "select district.ma_district, city.ma_city from city inner join district on city.id = district.ma_city where city.id = 1",nativeQuery=true)
	List<Object> findListDistrictOfDaNang();

}
