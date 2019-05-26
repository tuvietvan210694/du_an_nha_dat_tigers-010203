package com.example.demo.service.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.entity.response.AdminResponse;
import com.example.demo.entity.response.BrokerResponse;
import com.example.demo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public AdminResponse convertToResponse(Admin admin) {
		
		String date;
		//User user = userRepository.findByBroker(bro);
		User user = admin.getUser();
		System.out.println("ghghghgghghghghghghhg");
		if(user == null) {
			return null;
		}	
		AdminResponse br = new AdminResponse();
		br.setId(admin.getId());
		br.setActive(user.getActive());
		br.setAddress(user.getAddress());
		//br.setDateOfBirth(user.getDayOfBirth().toString());
		
		DateFormat format1 = new SimpleDateFormat(
				"yyyy-MM-dd");
//		Date date = format.parse(user.getDayOfBirth().toString());
		date = format1.format(user.getDayOfBirth());
		br.setDateOfBirth(date);
		
		
		br.setEmail(user.getEmail());
		br.setGender(user.getGender());
		br.setNameAdmin(user.getNameUser());
		br.setNonDel(user.getNonDel());
		br.setPhoneNumber(user.getPhoneNumber());
		br.setLinkImageProfile("https://tiger010203.herokuapp.com/downloadFileProfile" + "/" + user.getdBFileProfile().getId());
		return br;
	}

}
