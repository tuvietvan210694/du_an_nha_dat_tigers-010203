package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@RestController
public class UserApi {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/userapi")
	public ResponseEntity<Object> viewCurrentUser() {
		List<User> listUser = userRepository.findAll();
		if(listUser.size() == 0) {
			return new ResponseEntity<>("Danh sach rong", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listUser, HttpStatus.OK);
	}

//	
//	@GetMapping(path = "/userapi")
//	public ResponseEntity<Object> ViewCurrentUser() { 
//    List<User> listUser = userRepository.find
//	return new ResponseEntity<>(listUser, HttpStatus.OK);
//}

//test thu bai phan trang

@GetMapping(path = "/testUser")
public ResponseEntity<Object> filterDevDeRe(Pageable page) {
	
	System.out.println("Test thu phan trang trong spring");
	System.out.println(page.getPageSize());
	System.out.println("--------------------------------");
	List<User> listUser = userRepository.findAll();
	if(listUser.size() == 0) {
		//return new ResponseEntity<>()
	}
	
//	if(filter.isEmpty()) {
//		return new ResponseEntity<>("fill to search...", HttpStatus.OK);
//	}
//	Page<EmpDeviceResponse> pageDevDeRe = device_Deliver_ReceiveService.filterDevDeRe(filter, page);
//	//sua tai day
//	System.out.println("Edit here");
//	System.out.println(pageDevDeRe.getSize());
//	System.out.println("show"+ page.getPageSize() + "ghghghg" + pageDevDeRe.getTotalPages());
//	return new ResponseEntity<>(pageDevDeRe, HttpStatus.OK);
	return null;	
}

@GetMapping(path = "/getUsers/{pageIndex}")
public ResponseEntity<Object> getUsers(@PathVariable("pageIndex") int pageIndex, Pageable page){
	Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 2, Sort.by("email").descending());
	//link: https://www.baeldung.com/spring-data-jpa-pagination-sorting
	return new ResponseEntity<>(userService.getUsers(firstPageWithTwoElements), HttpStatus.OK);
}

}