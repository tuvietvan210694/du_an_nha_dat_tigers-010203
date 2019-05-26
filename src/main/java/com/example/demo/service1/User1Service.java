package com.example.demo.service1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.entity.User1;
import com.example.demo.repository.User2Repository;
import com.example.demo.repository.UserRepository;

@Service
public class User1Service {
	
	@Autowired
	private User2Repository user2Repository;
	
	@Autowired
	private UserRepository userRepository;
	
//  public static List<User1> listUser = new ArrayList<>();
//  //public static List<User1> listUser1 = new ArrayList<>();
//  static {
//    //User1 userKai = new User1(1, "kai", "123456",new String[] { "ROLE_ADMIN" });
//    //userKai.setRoles(new String[] { "ROLE_ADMIN" });
//    //User1 userSena = new User1(2, "sena", "123456", new String[] { "ROLE_USER" });
//	  
//	  
////	  listUser = user2Repository.findAll();
////	  for(User1 user1: listUser) {
////		  listUser1.add(user1);
////	  }
//	  //listUser = user2Repository.findAll();
//	  User1 userKai = new User1(1,"kai","123456","ROLE_ADMIN");
//	  User1 userSena = new User1(2, "sena", "123456", "ROLE_BROKER");
//	  listUser.add(userKai);
//	  listUser.add(userSena);
//	  //userSena.setRoles(new String[] { "ROLE_USER" });
//	  //listUser = user2Repository.findAll();
//  }
  public List<User> findAll() {
    return user2Repository.findAll();
  }
  
  public User findById(int id) {
	  List<User> listUser = user2Repository.findAll();
    for (User user : listUser) {
      if (user.getId() == id) {
        return user;
      }
    }
    return null;
  }
  
//  public boolean add(User1 user) {
//    for (User1 userExist : listUser) {
//      if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
//        return false;
//      }
//    }
//    listUser.add(user);
//    return true;
//  }
//  public void delete(int id) {
//    listUser.removeIf(user -> user.getId() == id);
//  }
  public User loadUserByUsername(String username) {
      List<User> listUser = user2Repository.findAll();
	  for (User user : listUser) {
      if (user.getEmail().equals(username)) {
        return user;
      }
    }
    return null;
  }
//  public boolean checkLogin(User user) {
//	  List<User> listUser = user2Repository.findAll();
//    for (User userExist : listUser) {
//      if (StringUtils.equals(user.getEmail(), userExist.getEmail())
//          && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
//        return true;
//      }
//    }
//    return false;
//  }
  
  public boolean checkLogin(User user) {
	 // List<User> listUser = user2Repository.findAll();
	  Optional<User> m = userRepository.findByEmail(user.getEmail());
     if(m.isPresent()) {
      if (StringUtils.equals(user.getPassword(), m.get().getPassword())) {
        return true;
      }
    
    return false;
  }
     return false;
  
}
}

