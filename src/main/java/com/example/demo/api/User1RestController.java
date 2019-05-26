package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BlockUser;
import com.example.demo.entity.User;
import com.example.demo.entity.response.TokenResponse;
import com.example.demo.repository.BlockUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service1.Jwt1Service;
import com.example.demo.service1.User1Service;
import com.example.demo.util.VerificationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Transactional
@RequestMapping("/rest")
public class User1RestController {
	
	
	@Autowired
	  private Jwt1Service jwtService;
	
	@Autowired
	  private User1Service userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationUtil veritificationUtil;
	
	@Autowired
	private BlockUserRepository blockUserRepository;
	
	/* ---------------- GET ALL USER ------------------------ */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
	    return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	  }
	
	/* ---------------- GET USER BY ID ------------------------ */
	  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	  public ResponseEntity<Object> getUserById(@PathVariable int id) {
	    User user = userService.findById(id);
	    if (user != null) {
	      return new ResponseEntity<Object>(user, HttpStatus.OK);
	    }
	    return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
	  }
//	
//	  /* ---------------- CREATE NEW USER ------------------------ */
//	  @RequestMapping(value = "/users", method = RequestMethod.POST)
//	  public ResponseEntity<String> createUser(@RequestBody User1 user) {
//	    if (userService.add(user)) {
//	      return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
//	    } else {
//	      return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
//	    }
//	  }
	
//	  /* ---------------- DELETE USER ------------------------ */
//	  @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
//	  public ResponseEntity<String> deleteUserById(@PathVariable int id) {
//	    userService.delete(id);
//	    return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
//	  }
	  
	  @RequestMapping(value = "/login", method = RequestMethod.POST)
	  public ResponseEntity<Object> login(HttpServletRequest request, @RequestBody User user) {
	    String result = "";
	    HttpStatus httpStatus = null;
	    Boolean flag = false;
//	    List<User> listUser = userRepository.findAll();
	    Optional<User> u = userRepository.findByEmail(user.getEmail());
	    if(u.isPresent()) {
	    	flag = true;
	    }
	    
//	    for(User m: listUser) {
//	    	if(m.getEmail() == user.getEmail()) {
//	    		flag = true;
//	    		break;
//	    	}
//	    }
	    try {
	    	if(flag == true) {
	    		if (user.getNonDel()) {
	    		  if (userService.checkLogin(user)) {
	    	        result = jwtService.generateTokenLogin(user.getEmail());
	    	        httpStatus = HttpStatus.OK;
	    	        TokenResponse tr = new TokenResponse();
	    	        tr.setUsername(u.get().getEmail());
	    	        tr.setAccessToken(result);
	    	        tr.setType("vanvanvan");
//	    	        List<String> a = new ArrayList<>();
//	    	        String b;
//	    	        ObjectMapper mapper = new ObjectMapper();
//	    	        List<GrantedAuthority> li = u.get().getAuthorities();
////	    	        for(GrantedAuthority grant: li) {
////	    	        	b = mapper.writeValueAsString(grant);
////	    	        	a.add(b);
////	    	        	
//	    	        }
//	    	        
	    	        
//	    	        String json = mapper.writeValueAsString(li);
//	    	        List<String> a = new ArrayList<>();
//	    	        a.add(json);
//	    	        String[] array = a.toArray(new String[a.size()]);
	    	        
	    	        //String[] array = u.get().getAuthorities().toArray(new String[u.get().getAuthorities().size()]);
	    	        String role = u.get().getRole().getRoleName();
	    	        List<String> list = new ArrayList<>();
	    	        list.add(role);
	    	        String[] array = list.toArray(new String[list.size()]);
	    	        
	    	        tr.setAuthorities(array);
	    	        return new ResponseEntity<>(tr, httpStatus);
	    	      } else {
	    	        result = "Wrong userId and password";
	    	        httpStatus = HttpStatus.BAD_REQUEST;
	    	        System.out.println("In ra so lan truot tai day...");
	    	        User u1 = u.get();
	    	        BlockUser blockUser = u1.getBlockUser();
	    	        if (blockUser == null) {
						BlockUser newBlockuser = new BlockUser();
						newBlockuser.setUser(u.get());
						newBlockuser.setBlockTime(null);
						newBlockuser.setNumberFail(1);
						blockUserRepository.save(newBlockuser);
						//user.setBlockUser(newBlockuser);
						System.out.println("blockUser null");
						//userRepository.save(user);
						return new ResponseEntity<>(result, httpStatus);
					}
	    	        
	    	        System.out.println("Test thu number fail");
					int num_fails = blockUser.getNumberFail();
					num_fails = num_fails + 1;
					if (num_fails >= 5) {
						System.out.println("Numfails >= 5");
						u1.setNonLocked(false);
						userRepository.save(u1);
						blockUser.setNumberFail(num_fails);
						blockUser.setBlockTime(veritificationUtil.calculatorExpireTime());
						System.out.println("thoi gian bi khoa" + veritificationUtil.calculatorExpireTime());
						//user.setBlockUser(blockUser);
						//userRepository.save(user);
						blockUser.setUser(u1);
						blockUserRepository.save(blockUser);
						return new ResponseEntity<>(result, httpStatus);
						
					} else {
						System.out.println("Numfials < 5");
						blockUser.setNumberFail(num_fails);
						blockUser.setUser(u1);
						//user.setBlockUser(blockUser);
						//userRepository.save(user);
						blockUserRepository.save(blockUser);
						return new ResponseEntity<>(result, httpStatus);
					}
	    	        
	    	        
	    	        
	    	      }
	    	}
	    	} else {
	    		result = "Wrong userId and password";
    	        httpStatus = HttpStatus.BAD_REQUEST;
	    	}
	      
	    } catch (Exception ex) {
	      result = "Server Error";
	      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return new ResponseEntity<>(result, httpStatus);
	  }
	  
}
