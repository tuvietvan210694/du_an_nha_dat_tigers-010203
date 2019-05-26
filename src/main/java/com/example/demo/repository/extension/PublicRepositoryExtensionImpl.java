package com.example.demo.repository.extension;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.District;
import com.example.demo.entity.Product;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.entity.response.PublicResponse;
import com.example.demo.util.QueryUtils;
import com.example.demo.util.StringUtils;


public class PublicRepositoryExtensionImpl implements PublicRepositoryExtension {
    
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<PublicResponse> getPublic(QueryParam<PublicResponse> page) {
		String sql = "SELECT pr FROM Product pr";
		String countSql = "SELECT count(1) FROM Product pr";
		String condition = " WHERE ";
		System.out.println(condition);
		System.out.println(page.getQuery());
		System.out.println(page.getPagingItem());
		String fCondition = filterCondition(page.getQuery());
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
        List<Product> listPr = query.getResultList();
        List<PublicResponse> data = new ArrayList<>();
        PublicResponse pr;
        for(Product p: listPr) {
        	pr = new PublicResponse();
        	pr.setId(p.getId());
        	pr.setCatalogDetailId(p.getCatalogDetail().getId());
        	pr.setProductLocation(p.getProductLocation());
        	pr.setProductId(p.getId());
        	pr.setProductStatus(p.getProductStatus());
        	pr.setNonDel(p.getNonDel());
        	pr.setProductName(p.getProductName());
        	pr.setProductCost(p.getProductCost());
        	pr.setProductImage(p.getProductImage());
        	pr.setRequestStatus(p.getRequestStatus());
        	pr.setCurrencyName(p.getCurrency().getCurrencyName());
        	pr.setdBFile(p.getdBFile());
        	data.add(pr);      	
        } 
		
		return data;
	}
	
	
	private String filterCondition(PublicResponse publicResponse) {
		List<String> fCondition = new ArrayList<>();
		
		if(publicResponse.getCatalogDetailId() != null) {
			fCondition.add("(pr.catalogDetail.id = "+publicResponse.getCatalogDetailId()+")");
		}
		if(StringUtils.isValidString(publicResponse.getProductLocation())) {
//			fCondition.add(QueryUtils.likeWithAsciiAndLower("pr.productLocation",publicResponse.getProductLocation()));
			fCondition.add(QueryUtils.like("pr.zipCode",publicResponse.getProductLocation()));
		
	    }
//		if(StringUtils.isValidString(publicResponse.getProductLocation())) {
//			fCondition.add("(pr.productLocation = "+publicResponse.getProductLocation()+")");
//		
//	    }
		return String.join(" AND ", fCondition);
	}

}
