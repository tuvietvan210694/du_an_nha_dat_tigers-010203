package com.example.demo.repository;
import com.example.demo.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.repository.extension.District1RepositoryExtension;

public interface District1Repository extends JpaRepository<District, Long>, District1RepositoryExtension {

	//List<DistrictResponse> getDistrict1(QueryParam<DistrictResponse> page);

}
