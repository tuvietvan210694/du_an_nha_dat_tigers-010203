//package com.example.demo.api;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.example.demo.entity.User2;
//import com.example.demo.service2.Jwt2Service;
//import com.example.demo.service2.User2Service;
//
//@RestController
//@RequestMapping("/rest2")
//public class User2RestController {
//	
//	@Autowired
//	  private Jwt2Service jwtService;
//	
//	@Autowired
//	  private User2Service userService;
//	
//	/* ---------------- GET ALL USER ------------------------ */
//	@RequestMapping(value = "/users", method = RequestMethod.GET)
//	public ResponseEntity<Object> getAllUser() {
//	    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
//	  }
//	
//	/* ---------------- GET USER BY ID ------------------------ */
//	  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//	  public ResponseEntity<Object> getUserById(@PathVariable int id) {
//	    User2 user = userService.findById(id);
//	    if (user != null) {
//	      return new ResponseEntity<Object>(user, HttpStatus.OK);
//	    }
//	    return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
//	  }
//	
//	  /* ---------------- CREATE NEW USER ------------------------ */
//	  @RequestMapping(value = "/users", method = RequestMethod.POST)
//	  public ResponseEntity<String> createUser(@RequestBody User2 user) {
//	    if (userService.add(user)) {
//	      return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
//	    } else {
//	      return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
//	    }
//	  }
//	
//	  /* ---------------- DELETE USER ------------------------ */
//	  @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
//	  public ResponseEntity<String> deleteUserById(@PathVariable int id) {
//	    userService.delete(id);
//	    return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
//	  }
//	  
//	  @RequestMapping(value = "/login", method = RequestMethod.POST)
//	  public ResponseEntity<String> login(HttpServletRequest request, @RequestBody User2 user) {
//	    String result = "";
//	    HttpStatus httpStatus = null;
//	    try {
//	      if (userService.checkLogin(user)) {
//	        result = jwtService.generateTokenLogin(user.getUsername());
//	        httpStatus = HttpStatus.OK;
//	      } else {
//	        result = "Wrong userId and password";
//	        httpStatus = HttpStatus.BAD_REQUEST;
//	      }
//	    } catch (Exception ex) {
//	      result = "Server Error";
//	      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//	    }
//	    return new ResponseEntity<String>(result, httpStatus);
//	  }
//}
