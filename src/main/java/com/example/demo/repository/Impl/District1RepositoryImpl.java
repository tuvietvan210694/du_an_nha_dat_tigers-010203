package com.example.demo.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.example.demo.domain.QueryParam;
import com.example.demo.entity.District;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.repository.District1Repository;
import com.example.demo.repository.extension.District1RepositoryExtension;
import com.example.demo.util.QueryUtils;
import com.example.demo.util.StringUtils;

public class District1RepositoryImpl implements District1RepositoryExtension  {
    
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<DistrictResponse> getDistrict1(QueryParam<DistrictResponse> page) {
		
		
		String sql = "SELECT dt FROM District dt";
		String countSql = "SELECT count(1) FROM District dt";
		String condition = " WHERE ";
		System.out.println(condition);
		System.out.println(page.getQuery());
		System.out.println(page.getPagingItem());
		String fCondition = filterCondition(page.getQuery());
		System.out.println(fCondition);
		if(StringUtils.isValidString(fCondition)) {
			sql = sql.concat(condition).concat(fCondition);
			countSql = countSql.concat(condition).concat(fCondition);
		}
		Query query = entityManager.createQuery(sql);
		Query countQuery = entityManager.createQuery(countSql);
		Long totalRows = (Long) countQuery.getSingleResult();
		System.out.println("total rows " + totalRows);
		page.getPagingItem().setOutRowsNumber(totalRows);
		query.setFirstResult((page.getPagingItem().getPageIndex() - 1 ) * page.getPagingItem().getPageSize());
        query.setMaxResults(page.getPagingItem().getPageSize());
        List<District> result = query.getResultList();
        List<DistrictResponse> data = new ArrayList<>();
        DistrictResponse dr;
        for(District d: result) {
        	dr = new DistrictResponse();
        	dr.setId(d.getId());
        	dr.setMaDistrict(d.getMaDistrict());
        	dr.setNameDistrict(d.getNameDistrict());
        	data.add(dr);      	
        } 
		return data;
	}

	private String filterCondition(DistrictResponse districtResponse) {
		List<String> fCondition = new ArrayList<>();
		
		if(districtResponse.getId() != null) {
			fCondition.add("(dt.id = "+districtResponse.getId()+")");
		}
		if(StringUtils.isValidString(districtResponse.getMaDistrict())) {
			fCondition.add(QueryUtils.like("dt.maDistrict",districtResponse.getMaDistrict()));
		
	    }
		if(StringUtils.isValidString(districtResponse.getNameDistrict())) {
			fCondition.add(QueryUtils.like("dt.nameDistrict",districtResponse.getNameDistrict()));
		
	    }
			
		return String.join(" AND ", fCondition);
	}

}
