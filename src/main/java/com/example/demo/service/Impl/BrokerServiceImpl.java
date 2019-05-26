package com.example.demo.service.Impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.Broker;
import com.example.demo.entity.User;
import com.example.demo.entity.response.BrokerResponse;
import com.example.demo.entity.response.UserSearchResponse;
import com.example.demo.repository.BrokerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BrokerService;



@Service
public class BrokerServiceImpl implements BrokerService {
	
    @Autowired 
    private BrokerRepository brokerRepository;
    
//    @Autowired
//    private UserRepository userRepository;
    
    @Autowired
    private BrokerService brokerService;
    
	@Override
	public List<Broker> findAll() {
		// TODO Auto-generated method stub
		List<Broker> listBroker = brokerRepository.findAll();
		if(listBroker.size() == 0) {
			return null;
		}
		return listBroker;
	}
	
	@Override
	public Broker getBrokerById(long id) {
		// TODO Auto-generated method stub
		Broker broker = brokerRepository.getOne(id);
		if(broker == null) {
			return null;
		}
		return broker;
	}

	@Override
	public BrokerResponse convertToResponse(Broker bro) throws ParseException {
		String date;
		//User user = userRepository.findByBroker(bro);
		User user = bro.getUser();
		System.out.println("ghghghgghghghghghghhg");
		if(user == null) {
			return null;
		}	
		BrokerResponse br = new BrokerResponse();
		br.setId(bro.getId());
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
		br.setNameBroker(user.getNameUser());
		br.setNonDel(user.getNonDel());
		br.setPhoneNumber(user.getPhoneNumber());
		if(user.getdBFileProfile() != null) {
			br.setLinkImageProfile("https://tiger010203.herokuapp.com/downloadFileProfile" + "/" + user.getdBFileProfile().getId());
		}
		
		return br;
		
	}

	@Override
	public List<BrokerResponse> listBrokerByFilter(String search) throws ParseException {
		List<Broker> listBroker = brokerRepository.findByUserEmailContainingOrUserNameUserContainingOrUserPhoneNumberContainingOrUserAddressContaining(search, search, search, search);
		// TODO Auto-generated method stub
		List<BrokerResponse> listBroRes = new ArrayList<>();
		BrokerResponse broRes = null;
		for(Broker bro: listBroker) {
			broRes = brokerService.convertToResponse(bro);
			listBroRes.add(broRes);
		}
		return listBroRes;
	}

	@Override
	public List<Broker> findAll(Pageable firstPageWithTwoElements) {
		// TODO Auto-generated method stub
		Page<Broker> p = brokerRepository.findAll(firstPageWithTwoElements);
		List<Broker> list = p.getContent();
		return list;
	}

	@Override
	public List<Broker> findByNonDel(boolean b, Pageable firstPageWithTwoElements) {
		
		Page<Broker> p = brokerRepository.findByUserNonDel(b,firstPageWithTwoElements);
		List<Broker> list = p.getContent();
		return list;
	}

	@Override
	public List<Broker> findByNonDel(boolean b) {
		// TODO Auto-generated method stub
		List<Broker> list = brokerRepository.findByUserNonDel(b);
		if((list == null)||(list.size() == 0)) {
			return null;
		}
		return list;
	}

//	@Override
//	public List<UserSearchResponse> seek(QueryParam<UserSearchResponse> page) {
//		List<UserSearchResponse> list = brokerRepository.seek(page);
//		return list;
//	}

}
