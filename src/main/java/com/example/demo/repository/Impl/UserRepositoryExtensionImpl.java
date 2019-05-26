package com.example.demo.repository.Impl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.example.demo.domain.QueryParam;
import com.example.demo.entity.Broker;
import com.example.demo.entity.response.UserSearchResponse;
import com.example.demo.repository.extension.UserRepositoryExtension;
import com.example.demo.util.QueryUtils;
import com.example.demo.util.StringUtils;

public class UserRepositoryExtensionImpl implements UserRepositoryExtension {
    
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<UserSearchResponse> seek(QueryParam<UserSearchResponse> page) {
		
		String sql = "SELECT bro FROM Broker bro";
		String countSql = "SELECT count(1) FROM Broker bro";
		String condition = "WHERE";
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
		Query countQuery = entityManager.createQuery(sql);
		Long totalRows = (Long) countQuery.getSingleResult();
		System.out.println("total rows: " + totalRows);
		page.getPagingItem().setOutRowsNumber(totalRows);
		query.setFirstResult((page.getPagingItem().getPageIndex() - 1) * page.getPagingItem().getPageSize());
		query.setMaxResults(page.getPagingItem().getPageSize());
		List<Broker> listBroker = query.getResultList();
		List<UserSearchResponse> data = new ArrayList<>();
		UserSearchResponse userSearchResponse;
		for(Broker bro: listBroker) {
			userSearchResponse = new UserSearchResponse();
			userSearchResponse.setId(bro.getUser().getId());
			userSearchResponse.setActive(bro.getUser().getActive());
			userSearchResponse.setDayOfBirth(bro.getUser().getDayOfBirth());
			userSearchResponse.setEmail(bro.getUser().getEmail());
			userSearchResponse.setGender(bro.getUser().getGender());
			userSearchResponse.setName(bro.getUser().getNameUser());
			userSearchResponse.setNonDel(bro.getUser().getNonDel());
			userSearchResponse.setNonLocked(bro.getUser().getNonLocked());
			userSearchResponse.setPassword(bro.getUser().getPassword());
			userSearchResponse.setPhoneNumber(bro.getUser().getPhoneNumber());
			data.add(userSearchResponse);
			
		}
		return data;
	}
	

	private String filterCondition(UserSearchResponse userSearchResponse) {
		List<String> fCondition = new ArrayList<>();
		if(userSearchResponse.getId() != null) {
			fCondition.add("(bro.user.id = "+ userSearchResponse.getId()+")");
		}
		if(StringUtils.isValidString(userSearchResponse.getEmail())) {
			fCondition.add(QueryUtils.like("bro.user.email",userSearchResponse.getEmail()));
	    }
		if(StringUtils.isValidString(userSearchResponse.getName())) {
			fCondition.add(QueryUtils.like("bro.user.nameUser", userSearchResponse.getName()));
		}
		if(StringUtils.isValidString(userSearchResponse.getPhoneNumber())) {
			fCondition.add(QueryUtils.like("bro.user.phoneNumber", userSearchResponse.getPhoneNumber()));
		}
		
		return String.join("AND", fCondition);
	}

}
