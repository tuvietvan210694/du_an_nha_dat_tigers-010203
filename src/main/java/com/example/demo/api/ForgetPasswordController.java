package com.example.demo.api;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.entity.TokenVerifition;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.EmailDTO;
import com.example.demo.entity.dto.PasswordDTO;
import com.example.demo.entity.dto.PasswordDTO1;
import com.example.demo.entity.dto.PasswordDTOProfile;
import com.example.demo.entity.response.ChangePasswordResponse;
import com.example.demo.entity.response.PasswordDTOProfileResponse;
import com.example.demo.entity.response.SignupDTOResponse;
import com.example.demo.repository.TokenVerifitionRepository;
import com.example.demo.repository.UserRepository;
//import com.example.demo.service.MailService;
import com.example.demo.service.TokenVerificationService;
import com.example.demo.service.UserService;
import com.example.demo.util.VerificationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.example.demo.service.EmailService;
import com.example.demo.service.PasswordService;


@RestController
@Transactional
//@Controller
public class ForgetPasswordController {
    
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private MailService mailService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationUtil verificationUtil;
	
	@Autowired
	private TokenVerificationService tokenVerificationService;
	
	@Autowired
	private TokenVerifitionRepository tokenVerifitionRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/forget-password")
	public Object checkOutEmailAndSendMail(@Valid @RequestBody EmailDTO emailDTO,
			BindingResult bindingResult) {
		
		String email = emailDTO.getEmail();
		System.out.println("forgetPassword"+ email);
		// tim kiem user co email la ?
	
		// not bank, dung dinh dang email
		if (bindingResult.hasErrors()) {
			String fieldName = bindingResult.getFieldError().getField();
			String message = bindingResult.getFieldError().getDefaultMessage();
			return new ResponseEntity<>(fieldName+" : "+message,HttpStatus.BAD_REQUEST);
		}
		User existEmail = userService.findByEmail(email);
		if(existEmail == null || !existEmail.getNonDel()) {
			return new ResponseEntity<>("Email nay khong ton tai trong he thong", HttpStatus.NOT_FOUND);
		}
		String registCode = verificationUtil.generateVerificationCode(email);
		Date expireDate = verificationUtil.calculatorExpireTime();
		System.out.println(registCode);
		try {
			User user = userService.findByEmail(email);
			// neu da goi email roi thi ra ve thong bao
			if (user.getTokenVerifition() != null) {
				System.out.println("kiem tra mail");
				return new ResponseEntity<>("Sent email please check email", HttpStatus.OK);
			} else {
				// goi email
				System.out.println("kiem tra mail");
//				mailService.sendMail("FORGET PASSWORD", "/change-password", email,
//						registCode, expireDate);
				
				//mailService.sendMail("FORGET PASSWORD", "/change-password", email, registCode, expireDate);
				//mailService.sendMail();
				
				
				Long user_id = user.getId();
				tokenVerificationService.addTokenFunction(expireDate, registCode, user_id);
				String link = "https://tiger010203.herokuapp.com/change-password?token=" + registCode;
				emailService.sendHTML("vuhiepdng@gmail.com", email , "Hello World", "Hello, <a href = " + link + ">"+ "how are you doing?</a>");
				
				return new ResponseEntity<>("vao trong goi mail", HttpStatus.OK);	
				
			     //return new ResponseEntity<>()
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		//return new ResponseEntity<>("Xin chao Tu Viet Van", HttpStatus.OK);
	
		return new ResponseEntity<>(registCode + "  " + expireDate, HttpStatus.OK);	
}
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/forget-password1")
	public Object checkOutEmailAndSendMail1(@Valid @RequestBody EmailDTO emailDTO,
			BindingResult bindingResult) {
		
		String email = emailDTO.getEmail();
		System.out.println("forgetPassword"+ email);
		// tim kiem user co email la ?
	
		// not bank, dung dinh dang email
		if (bindingResult.hasErrors()) {
			String fieldName = bindingResult.getFieldError().getField();
			String message = bindingResult.getFieldError().getDefaultMessage();
			return new ResponseEntity<>(fieldName+" : "+message,HttpStatus.OK);
		}
		User existEmail = userService.findByEmail(email);
		if(existEmail == null || !existEmail.getNonDel()) {
			return new ResponseEntity<>("Email nay khong ton tai trong he thong", HttpStatus.OK);
		}
		String registCode = verificationUtil.generateVerificationCode(email);
		Date expireDate = verificationUtil.calculatorExpireTime();
//		System.out.println(registCode);
		try {
			User user = userService.findByEmail(email);
			user.setPassword(registCode);
			userRepository.save(user);
			// neu da goi email roi thi ra ve thong bao
//			if (user.getTokenVerifition() != null) {
//				System.out.println("kiem tra mail");
//				return new ResponseEntity<>("Sent email please check email", HttpStatus.OK);
//			} else {
				// goi email
				//System.out.println("kiem tra mail");
//				mailService.sendMail("FORGET PASSWORD", "/change-password", email,
//						registCode, expireDate);
				
				//mailService.sendMail("FORGET PASSWORD", "/change-password", email, registCode, expireDate);
				//mailService.sendMail();
				
				
				//Long user_id = user.getId();
				//tokenVerificationService.addTokenFunction(expireDate, registCode, user_id);
//				TokenVerifition tokenVerifition = new TokenVerifition();
//				tokenVerifition.setTokenCode(registCode);
//				tokenVerifition.setExpireTime(expireDate);
//				tokenVerifition.setUser(user);
//				tokenVerifitionRepository.save(tokenVerifition);
				
				String link = "https://tiger010203.herokuapp.com/change-password1?token=" + registCode;
				emailService.sendHTML("tuvietvancntt@gmail.com", email , "Forget Password!!!", "Hello, Mật khẩu đăng nhập của bạn là: "+ registCode + " <a href = " + link + ">"+ "Login</a>");
				
				return new ResponseEntity<>("Lam on kiem tra mail cua ban!!!", HttpStatus.OK);	
				
			     //return new ResponseEntity<>()
//			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		//return new ResponseEntity<>("Xin chao Tu Viet Van", HttpStatus.OK);
	
		return new ResponseEntity<>(registCode +expireDate.toString() , HttpStatus.OK);	
}
	
	@GetMapping("/change-password1")
	public String changePass(@RequestParam("token") String token, ModelMap modelMap, HttpServletResponse r ) throws IOException {
		r.sendRedirect("http://localhost:4200/login");
		return "ghghg";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/change-password")
	public String viewChangePassword(@RequestParam("token") String token, ModelMap modelMap, HttpServletResponse r ) throws IOException {
		TokenVerifition checkToKenVerifition = tokenVerificationService.findTokenByTokenCode(token);
		User u = checkToKenVerifition.getUser();
		String mail = u.getEmail();
		if(checkToKenVerifition == null) {
			//return new ResponseEntity<>("token bi loi roi", HttpStatus.NOT_FOUND);
			return "Token bi loi roi";
		} else {
			Date nowDate = new Date();
			// kiem tra het thoi gian qua han
			if (checkToKenVerifition.getExpireTime().getTime() < nowDate.getTime()) {
				checkToKenVerifition.setExpireTime(verificationUtil.calculatorExpireTime());
				checkToKenVerifition.setTokenCode(verificationUtil.generateVerificationCode(checkToKenVerifition.getUser().getEmail()));
				tokenVerifitionRepository.save(checkToKenVerifition);
//				try {
//					//mailService.sendMail("FORGET PASSWORD","/change-password",checkToKenVerifition.getUser().getEmail(),checkToKenVerifition.getTokenCode(),checkToKenVerifition.getExpireTime());
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				}
				//return new ResponseEntity<>("Token het han roi", HttpStatus.NOT_FOUND);
				return "Token het han roi";
				
			}
			ChangePasswordResponse cpr = new ChangePasswordResponse();
			cpr.setEmail(mail);
			cpr.setToken(token);
//			PasswordDTO passwordDTO = new PasswordDTO();
//			passwordDTO.setEmail(checkToKenVerifition.getUser().getEmail());
//			passwordDTO.setToken(token);
//			passwordDTO.setPasswordCurrent("a");
//			modelMap.addAttribute("changePasswordDTO", passwordDTO);
			
			//return new ResponseEntity<>(cpr, HttpStatus.OK);
			r.sendRedirect("http://bongdaplus.vn/");
			return "http://bongdaplus.vn/";
			
		}	
	}
	@PostMapping("/change-password")
	public ResponseEntity<Object> toDoChangePassword(@Valid @RequestBody PasswordDTO1 passwordDTO1,
			BindingResult bindingResult) {
		String email = passwordDTO1.getEmail();
		String token = passwordDTO1.getToken();
		User user = userService.findByEmail(email);
		
		String dbPassword = user.getPassword(); 
		String newPassword = passwordDTO1.getNewPassword();
		String newMatchingPassword= passwordDTO1.getNewMatchingPassword();
		PasswordDTO passwordDTO = new PasswordDTO();
		passwordDTO.setToken(token);
		passwordDTO.setEmail(email);
		passwordDTO.setPasswordCurrent(dbPassword);
		passwordDTO.setNewPassword(newPassword);
		passwordDTO.setNewMatchingPassword(newMatchingPassword);		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().toString());
			List<String> messages = new ArrayList<>();
			List<ObjectError> list = bindingResult.getAllErrors();
			for (ObjectError objectError : list) {
				messages.add(objectError.getDefaultMessage());
				
			}
			return new ResponseEntity<>(messages, HttpStatus.OK);
//			modelMap.addAttribute("changePasswordDTO", passwordDTO);
//			return "change-password";			
		}
		boolean checkDuplicateMatchingPassword = passwordService.checkDuplicateMatchingPassword(newPassword, newMatchingPassword);
		if(!checkDuplicateMatchingPassword) {
			return new ResponseEntity<>("Xác nhận password mới không trùng với password mới", HttpStatus.NOT_FOUND);
		}
		
		user.setPassword(newPassword);
		userRepository.save(user);
		
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@PostMapping("/detail-change-password")
	public ResponseEntity<Object> toDoChangePasswordDetail(@Valid @RequestBody PasswordDTO passwordDTO,
			BindingResult bindingResult, Principal p) { 
	    //String email = p.getName();
		String email = "tuvietvancntt@gmail.com";
	    User user = userService.findByEmail(email);
	    if(user == null) {
	    	return new ResponseEntity<>("Not Found this user", HttpStatus.NOT_FOUND);
	    }
	    String dbPassword = user.getPassword(); 
	    String newPassword = passwordDTO.getNewPassword();
	    String newMatchingPassword= passwordDTO.getNewMatchingPassword();
	    if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().toString());
			List<String> messages = new ArrayList<>();
			List<ObjectError> list = bindingResult.getAllErrors();
			for (ObjectError objectError : list) {
				messages.add(objectError.getDefaultMessage());
				
			}
			return new ResponseEntity<>(messages, HttpStatus.OK);
//			modelMap.addAttribute("changePasswordDTO", passwordDTO);
//			return "change-password";			
		}
	    boolean checkDuplicateMatchingPassword = passwordService.checkDuplicateMatchingPassword(newPassword, newMatchingPassword);
	    if(!checkDuplicateMatchingPassword) {
			return new ResponseEntity<>("Xác nhận password mới không trùng với password mới", HttpStatus.NOT_FOUND);
		}
		
	    user.setPassword(newPassword);
		userRepository.save(user);
	    
	 return new ResponseEntity<>(user, HttpStatus.OK);
	
}

	
	@PostMapping("/change-password-profile")
	public ResponseEntity<Object> ChangePassword(@Valid @RequestBody PasswordDTOProfile passwordDTOProfile,
			BindingResult bindingResult, Principal p) throws JsonProcessingException {
		String email = p.getName();
		//String email = "huynhtruongphuc@gmail.com";
		Optional<User> u = userRepository.findByEmail(email);
		if(!u.isPresent()) {
			return new ResponseEntity<>("User not found....", HttpStatus.NOT_FOUND);
		}
		User user = u.get();
		PasswordDTOProfileResponse passwordDTOProfileResponse = new PasswordDTOProfileResponse();
		if(bindingResult.hasErrors()){          
	    	  System.out.println(bindingResult.getAllErrors().toString());
	          Map<String, String> errors = bindingResult.getFieldErrors().stream()
	                .collect(
	                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
	                  );	        
//	          if(result.getAllErrors().toString().indexOf("PasswordMatches")!= -1) {
//	        	  errors.put("matchingPassword", "Password is not matched");
//	          }c
	          passwordDTOProfileResponse.setValidated(false);
	          passwordDTOProfileResponse.setErrorMessages(errors); 
	          ObjectMapper mapper = new ObjectMapper();
	          String json = mapper.writeValueAsString(passwordDTOProfileResponse);
	          
	          Map<String, String> map = passwordDTOProfileResponse.getErrorMessages();
	          
	          Set set=map.entrySet();//Converting to Set so that we can traverse  
	          Iterator itr=set.iterator(); 
	          String message = "";
	          while(itr.hasNext()){  
	              //Converting to Map.Entry so that we can get key and value separately  
	              Map.Entry entry=(Map.Entry)itr.next();  
	              //System.out.println(entry.getKey()+" "+entry.getValue());  
	             message  = message + " - " + entry.getValue();
	          }  
	          
	          return new ResponseEntity<Object>(message, HttpStatus.OK);
	      }
		
		
//		String email = passwordDTO1.getEmail();
//		String token = passwordDTO1.getToken();
//		User user = userService.findByEmail(email);
//		
		String dbPassword = user.getPassword();
		String currentPassword = passwordDTOProfile.getPasswordCurrent();
		String newPassword = passwordDTOProfile.getNewPassword();
		String newMatchingPassword = passwordDTOProfile.getNewMatchingPassword();
		
		if(!currentPassword.equals(dbPassword)) {
			return new ResponseEntity<>("Current Password not property", HttpStatus.OK);
		}
		if(!newPassword.equals(newMatchingPassword)) {
			return new ResponseEntity<>("New Password not equal with New Matching Password!", HttpStatus.OK);
		}
		user.setPassword(newPassword);
		userRepository.save(user);
		
//		String newPassword = passwordDTO1.getNewPassword();
//		String newMatchingPassword= passwordDTO1.getNewMatchingPassword();
//		PasswordDTO passwordDTO = new PasswordDTO();
//		passwordDTO.setToken(token);
//		passwordDTO.setEmail(email);
//		passwordDTO.setPasswordCurrent(dbPassword);
//		passwordDTO.setNewPassword(newPassword);
//		passwordDTO.setNewMatchingPassword(newMatchingPassword);		
//		if (bindingResult.hasErrors()) {
//			System.out.println(bindingResult.getAllErrors().toString());
//			List<String> messages = new ArrayList<>();
//			List<ObjectError> list = bindingResult.getAllErrors();
//			for (ObjectError objectError : list) {
//				messages.add(objectError.getDefaultMessage());
//				
//			}
//			return new ResponseEntity<>(messages, HttpStatus.OK);
////			modelMap.addAttribute("changePasswordDTO", passwordDTO);
////			return "change-password";			
//		}
//		boolean checkDuplicateMatchingPassword = passwordService.checkDuplicateMatchingPassword(newPassword, newMatchingPassword);
//		if(!checkDuplicateMatchingPassword) {
//			return new ResponseEntity<>("Xác nhận password mới không trùng với password mới", HttpStatus.NOT_FOUND);
//		}
//		
//		user.setPassword(newPassword);
//		userRepository.save(user);
//		
		
		//return new ResponseEntity<>(user, HttpStatus.OK);
		return new ResponseEntity<>("Da thay doi Password thanh cong!!!", HttpStatus.OK);
	}
	
	
	
	
	
}















