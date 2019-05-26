package com.example.demo.repository.extension;
import java.util.List;
import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.DistrictResponse;

public interface District1RepositoryExtension {
	
	List<DistrictResponse> getDistrict1(QueryParam<DistrictResponse> page);
}
