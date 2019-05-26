package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.entity.response.ResponseData;
import com.example.demo.service.District1Service;


@RestController
public class District1Api {
	@Autowired
	private District1Service district1Service;
	
	@PostMapping(path = "/district1")
	public ResponseEntity<Object> listDistrict(@RequestBody QueryParam<DistrictResponse> page) {
		ResponseData res = new ResponseData();
		try {
			List<DistrictResponse> data = district1Service.getDistrict1(page);
			res.setTotalPage(page.getPagingItem().getNumberOfPage());
			res.setTotalRows(page.getPagingItem().getOutRowsNumber());
			Long rowNumber = page.getPagingItem().getOutRowsNumber();
			System.out.println("in ra out rows number");
			System.out.println(rowNumber);
			int pageSize = page.getPagingItem().getPageSize();
			res.setTotalPage((rowNumber % pageSize==0)? rowNumber/pageSize : rowNumber/pageSize+1);
			res.setData(data);	
			
		} catch(Exception ex) {
			System.out.println("vvvvvv");
			System.out.println(ex);
			res.setError(ex);
			res.setMessage(ex.getMessage());
			res.setSuccess(false);
			//logger.error(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
