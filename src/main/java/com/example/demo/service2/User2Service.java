//package com.example.demo.service2;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.example.demo.entity.User2;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.User2Repository;
//
//@Service
//public class User2Service {
//	@Autowired
//	private User2Repository user2Repository;
//	@Autowired
//	private RoleRepository roleRepository;
//	
//	static List<User2> list = new ArrayList<User2>();
//  static {
//	  
//	  //list = user2Repository.findAll();
//    User2 userKai = new User2();
//    userKai.setId(1L);
//    userKai.setUsername("abc@gmail.com");
//    userKai.setPassword("abcd1234@H");
//   // Optional<Role> ro = roleRepository.findByRoleName("ADMIN");
//    userKai.setRole("ADMIN");
//    
////    //userKai.setRoles(new String[] { "ROLE_ADMIN" });
////    User1 userSena = new User1(2, "sena", "123456", new String[] { "ROLE_USER" });
////    //userSena.setRoles(new String[] { "ROLE_USER" });
//    list.add(userKai);
//    //listUser.add(userSena);
//  }
//	
//	
//  public List<User2> findAll() {
//    return list;
//  }
//  
//  public User2 findById(long id) {
////    for (User1 user : listUser) {
////      if (user.getId() == id) {
////        return user;
////      }
////    }
////    return null;
//	  Optional<User2> user2 = user2Repository.findById(id);
//	  if(user2.isPresent()) {
//		  return user2.get();
//	  }
//	  return null;
//  }
//  
//  
//  public boolean add(User2 user) {
//	  
//    for (User2 userExist : list) {
//      if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
//        return false;
//      }
//    }
//    list.add(user);
//    return true;
//  }
//  
//  
//  public void delete(int id) {
//    list.removeIf(user -> user.getId() == id);
//  }
//  
//  public User2 loadUserByUsername(String username) {
//    for (User2 user : list) {
//      if (user.getUsername().equals(username)) {
//        return user;
//      }
//    }
//    return null;
//  }
//  
//  public boolean checkLogin(User2 user) {
//    for (User2 userExist : list) {
//      if (StringUtils.equals(user.getUsername(), userExist.getUsername())
//          && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
//        return true;
//      }
//    }
//    return false;
//  }
//}
