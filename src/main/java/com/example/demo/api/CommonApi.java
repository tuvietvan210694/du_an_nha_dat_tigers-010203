package com.example.demo.api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.QueryParam;
import com.example.demo.entity.Broker;
import com.example.demo.entity.Catalog;
import com.example.demo.entity.CatalogDetail;
import com.example.demo.entity.DBFile;
import com.example.demo.entity.DBFileProfile;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import com.example.demo.entity.TokenVerifition;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.BrokerDTO;
import com.example.demo.entity.dto.SignUp;
import com.example.demo.entity.response.DistrictResponse;
import com.example.demo.entity.response.ProductPublicResponse;
import com.example.demo.entity.response.PublicResponse;
import com.example.demo.entity.response.ResponseData;
import com.example.demo.entity.response.SignupDTOResponse;
import com.example.demo.repository.BrokerRepository;
import com.example.demo.repository.CatalogDetailRepository;
import com.example.demo.repository.CatalogRepository;
import com.example.demo.repository.DBFileProfileRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TokenVerifitionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.ProductService;
import com.example.demo.service.PublicService;
import com.example.demo.util.RequestState;
import com.example.demo.util.VerificationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Response;

@RestController
@Transactional
public class CommonApi {
	
	@Autowired
	private PublicService publicService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CatalogDetailRepository catalogDetailRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DBFileProfileRepository dBFileProfileRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationUtil verificationUtil;
	
	@Autowired
	private TokenVerifitionRepository tokenVerifitionRepository;
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	@Autowired
	private  BrokerRepository brokerRepository;
	
	
	
	
	@PostMapping(path = "/public")
	public ResponseEntity<Object> handlePublic(@RequestBody QueryParam<PublicResponse> page) {
		ResponseData res = new ResponseData();
		try {
			List<PublicResponse> data = publicService.getPublic(page);
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
	//trang public
	@GetMapping(path = "/searchPublic/{id}")
	public ResponseEntity<Object> handlePublicSimple(@PathVariable("id") long id, @RequestParam("zipcode") String zipcode) {
		
		Optional<CatalogDetail> catalogDetailOption = catalogDetailRepository.findById(id);
		if(!catalogDetailOption.isPresent()) {
			return new ResponseEntity<>(" Not found id of catalog detail", HttpStatus.NOT_FOUND);
		}
		CatalogDetail catalogDetail = catalogDetailOption.get(); 
		List<Product> listProduct = productRepository.findByCatalogDetailAndZipCodeAndRequestStatusAndProductStatus(catalogDetail, zipcode, RequestState.APPROVED, false);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>("Not found any product", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> listProPubRes = new ArrayList<>();
		ProductPublicResponse pro;
		for(Product product: listProduct) {
			pro = productService.convertToProductResponsePublic(product);
			listProPubRes.add(pro);
			
		}
		return new ResponseEntity<>(listProPubRes, HttpStatus.OK);
	}
	@GetMapping(path = "/detailProductPublic/{id}")
	public ResponseEntity<Object> detailProductPublic(@PathVariable("id") long id) {
		Optional<Product> product = productRepository.findById(id);
		if(!product.isPresent()) {
			return new ResponseEntity<>("Not found product have this id...", HttpStatus.NOT_FOUND);
		}
		Product pro = product.get();
		ProductPublicResponse p = productService.convertToProductResponsePublic(pro);
		if(p == null) {
			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listPostPublic/{pageIndex}")
	public ResponseEntity<Object> listPostPublic(@PathVariable("pageIndex") int pageIndex, Pageable page) {
		pageIndex = pageIndex - 1;
		Pageable phantrang = PageRequest.of(pageIndex, 4, Sort.by("id"));
		List<Product> listProduct = productService.findByRequestStatusAndProductStatus(RequestState.APPROVED, false, phantrang);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse pro;
		for(Product p: listProduct) {
			pro = productService.convertToProductResponsePublic(p);
			list.add(pro);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listAllPostPublic")
	public ResponseEntity<Object> listAllPostPublic() {
		List<Product> listProduct = productService.findByRequestStatusAndProductStatus(RequestState.APPROVED, false);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse pro;
		for(Product p: listProduct) {
			pro = productService.convertToProductResponsePublic(p);
			list.add(pro);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//register
	@PostMapping(path = "/signup")
	public ResponseEntity<Object> signUp(@Valid @RequestBody SignUp signUp, BindingResult result) throws ParseException, JsonProcessingException {
		SignupDTOResponse signupDTOResponse = new SignupDTOResponse();
		if(result.hasErrors()){          
	    	  System.out.println(result.getAllErrors().toString());
	          Map<String, String> errors = result.getFieldErrors().stream()
	                .collect(
	                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
	                  );	        
//	          if(result.getAllErrors().toString().indexOf("PasswordMatches")!= -1) {
//	        	  errors.put("matchingPassword", "Password is not matched");
//	          }c
	          signupDTOResponse.setValidated(false);
	          signupDTOResponse.setErrorMessages(errors); 
	          ObjectMapper mapper = new ObjectMapper();
	          String json = mapper.writeValueAsString(signupDTOResponse);
	          
	          Map<String, String> map = signupDTOResponse.getErrorMessages();
	          
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
		
		String email = signUp.getEmail();
		
		Optional<User> u = userRepository.findByEmail(email);
		if(u.isPresent()) {
			return new ResponseEntity<>("Da ton tai mail nay", HttpStatus.OK);
		}
		String name = signUp.getName();
		String address = signUp.getAddress();
		String dateOfBirth = signUp.getDateOfBirth();
		String phoneNumber = signUp.getPhoneNumber();
		Boolean gender = signUp.getGender();
		String password = signUp.getPassword();
		String passwordConfirm = signUp.getPasswordConfirm();
		if(!password.equals(passwordConfirm)) {
			return new ResponseEntity<>("Password Confirm not equal with password!!!", HttpStatus.OK);
		}
		
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
		
		
		Date date = format.parse(dateOfBirth);
		User user = new User();
		user.setEmail(email);
		user.setNameUser(name);
		user.setAddress(address);
		user.setDayOfBirth(date);
		user.setPhoneNumber(phoneNumber);
		user.setGender(gender);
		user.setPassword(password);
		user.setActive(false);
		user.setNonLocked(true);
		
//	    Optional<DBFileProfile> dBFileProfile = dBFileProfileRepository.findById(17L);
//	    if(!dBFileProfile.isPresent()) {
//	    	return new ResponseEntity<>("Sai profile id roi", HttpStatus.NOT_FOUND);
//	    }
//	    DBFileProfile db = dBFileProfile.get();
//	    user.setdBFileProfile(db);
		
	    Optional<Role> role = roleRepository.findById(3L);
	    if(!role.isPresent()) {
	    	return new ResponseEntity<>("Sai role id roi", HttpStatus.NOT_FOUND);
	    }
	    Role r = role.get();
	    user.setRole(r);
	    userRepository.save(user);
//	    
	    String registCode = verificationUtil.generateVerificationCode(email);
		Date expireDate = verificationUtil.calculatorExpireTime();
		
	    TokenVerifition t = new TokenVerifition();
	    t.setExpireTime(expireDate);
	    t.setTokenCode(registCode);
	    t.setUser(user);
	    tokenVerifitionRepository.save(t);
	    //fix code tai day!!!
	    Broker broker = new Broker();
	    broker.setUser(user);
	    brokerRepository.save(broker);
	    
	    if (user.getTokenVerifition() != null) {
			System.out.println("kiem tra mail");
			return new ResponseEntity<>("Sent email please check email", HttpStatus.OK);
		} else {
			String link = "https://tiger010203.herokuapp.com/activeAccount?token=" + registCode;
			emailService.sendHTML("tuvietvancntt@gmail.com", email , "Hello World", "Hello, <a href = " + link + ">"+ "Active?</a>");
	    
			//return new ResponseEntity<>(r1.getBody(), HttpStatus.OK);	
		}
	    
	    return new ResponseEntity<>("Làm ơn kiểm tra mail của bạn...", HttpStatus.OK);
	    
	    
//	    
//	    if(1==1) {
	    
		
		
//	    } 
	    
				
	}
	
	@GetMapping("/activeAccount")
	public ResponseEntity<Object> activeAccount(@RequestParam("token") String token, ModelMap modelMap, HttpServletResponse r ) throws IOException {
		
		
		Optional<TokenVerifition> checkToKenVerifition = tokenVerifitionRepository.findByTokenCode(token);
		TokenVerifition tokenV = checkToKenVerifition.get();
		User u = tokenV.getUser();
		u.setActive(true);
		userRepository.save(u);
		r.sendRedirect("http://localhost:4200/login");
		return new ResponseEntity<>("Ban da active thanh cong...", HttpStatus.OK);
	}
	
	@GetMapping(path = "/listCatalogPublic")
	public ResponseEntity<Object> listCatalog() {
		List<Catalog> listCatalog = catalogRepository.findAll();
		if(listCatalog == null || listCatalog.size() == 0) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listCatalog, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/catalogPublic/{id}")
	public ResponseEntity<Object> listCatalogDetailPublic(@PathVariable("id") Long id) {
		List<CatalogDetail> listCatalogDetail = catalogDetailRepository.findByCatalogId(id);
		if(listCatalogDetail == null || listCatalogDetail.size() == 0) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listCatalogDetail, HttpStatus.OK);
	}
	

}
