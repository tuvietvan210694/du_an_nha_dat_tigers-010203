package com.example.demo.api;
import com.example.demo.entity.Owner;
import com.example.demo.entity.CatalogDetail;
import com.example.demo.entity.City;
import com.example.demo.entity.Currency;
import com.example.demo.entity.CustomerNotAuthority;
import com.example.demo.entity.DBFile;
import com.example.demo.entity.DBFileProfile;
import com.example.demo.entity.District;

import java.io.IOException;
import java.security.Principal;
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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Broker;
import com.example.demo.entity.Catalog;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.Ward;
import com.example.demo.entity.dto.BrokerDTO;
import com.example.demo.entity.dto.BrokerDTOEdit;
import com.example.demo.entity.dto.EditBrokerDTO;
import com.example.demo.entity.dto.EditPostBrokerDTO;
import com.example.demo.entity.dto.NewPostDTO;
import com.example.demo.entity.dto.PasswordDTO;
import com.example.demo.entity.dto.PasswordDTOProfile;
import com.example.demo.entity.response.BrokerResponse;
import com.example.demo.entity.response.OwnerResponse;
import com.example.demo.entity.response.PasswordDTOProfileResponse;
import com.example.demo.entity.response.ProductPublicResponse;
import com.example.demo.repository.BrokerRepository;
import com.example.demo.repository.CatalogDetailRepository;
import com.example.demo.repository.CatalogRepository;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.CustomerNotAuthorityRepository;
import com.example.demo.repository.DBFileProfileRepository;
import com.example.demo.repository.DBFileRepository;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WardRepository;
import com.example.demo.service.BrokerService;
import com.example.demo.service.DBFileProfileService;
import com.example.demo.service.DBFileStorageService;
import com.example.demo.service.OwnerService;
import com.example.demo.service.PasswordService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.util.RequestState;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Transactional
@RequestMapping("/a2/broker")
public class BrokerApi {
    
	@Autowired
	private BrokerRepository brokerRepository;
	
	@Autowired
	private BrokerService brokerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private CatalogDetailRepository catalogDetailRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private ProductService productService;
	
	 @Autowired
	 private DBFileStorageService dBFileStorageService;
	 
	 @Autowired
	 private DBFileProfileService dBFileProfileService;
	 
	 @Autowired
	 private DBFileRepository dBFileRepository;
	 
	 @Autowired
	 private OwnerService ownerService;
	 
	 @Autowired
	 private CatalogRepository catalogRepository;
	 
	 @Autowired
	 private WardRepository wardRepository;
	 
	 @Autowired
	 private DBFileProfileRepository dBFileProfileRepository;
	 
	 @Autowired
	 private CustomerNotAuthorityRepository customerNotAuthorityRepository;
	
	/**
	 * @summary Broker infor invividual of mine.
	 * @date  2019
	 * @author Tu Viet Van
	 * @param  
	 * @return 
	 * @throws ParseException 
	 **/
	
	@GetMapping(path = "/detail")
	public ResponseEntity<Object> displayInfoBroker(Principal p) throws ParseException {
		//String email = "tuvietvancntt123@gmail.com";
		//String email = "abc@gmail.com";
		String email = p.getName();
		Broker bro = brokerRepository.findByUserEmail(email);
		if(bro == null) {
			return new ResponseEntity<>("Not Found This Broker", HttpStatus.NOT_FOUND);
			
		}
		BrokerResponse broRes = brokerService.convertToResponse(bro);
		if(broRes == null) {
			return new ResponseEntity<>("Error Display Information this Broker", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(broRes, HttpStatus.OK);	
	}
	
    @GetMapping(path = "/listOwnerNotPost")	
	public ResponseEntity<Object> displayListOwnerNotPost(Principal p) throws ParseException {
		
		//String email = "phuchuynhqn11y@gmail.com";
		String email = p.getName();
		
		List<Owner> listOwner = ownerRepository.findByBrokerUserEmailAndTrangThai(email, false);
		if((listOwner == null) || (listOwner.size() == 0)) {
			return new ResponseEntity<>(" Not Found", HttpStatus.NOT_FOUND);
		}
		List<OwnerResponse> listOwnerResponse = new ArrayList<>();
		OwnerResponse ownerResponse;
		for(Owner owner: listOwner) {
			ownerResponse = ownerService.convertToOwnerResponse(owner);
			listOwnerResponse.add(ownerResponse);
		}
		return new ResponseEntity<>(listOwnerResponse, HttpStatus.OK);
		
	}
	
    @GetMapping(path = "/listOwnerPosted")	
	public ResponseEntity<Object> displayListOwnerPosted(Principal p) throws ParseException {
		
		//String email = "tuvietvancntt123@gmail.com";
    	//String email = "phuchuynhqn11y@gmail.com";
    	String email = p.getName();
		
		List<Owner> listOwner = ownerRepository.findByBrokerUserEmailAndTrangThai(email, true);
		if((listOwner == null) || (listOwner.size() == 0)) {
			return new ResponseEntity<>(" Not Found", HttpStatus.NOT_FOUND);
		}
		List<OwnerResponse> listOwnerResponse = new ArrayList<>();
		OwnerResponse ownerResponse;
		for(Owner owner: listOwner) {
			ownerResponse = ownerService.convertToOwnerResponse(owner);
			listOwnerResponse.add(ownerResponse);
		}
		return new ResponseEntity<>(listOwnerResponse, HttpStatus.OK);
		
	}
	
	/*
	 * Get all the post in system
	 */
//	@GetMapping(path = "/getAllPost/{pageIndex}")
//	public ResponseEntity<Object> getAllPost(Principal p, @PathVariable("pageIndex") int pageIndex, Pageable page) {
//		//String email = p.getName();
//		String email = "phuchuynhqn11@gmail.com";
//		Optional<User> u = userRepository.findByEmail(email);
//		if(!u.isPresent()) {
//			return new ResponseEntity<>("Not found correct user", HttpStatus.NOT_FOUND);
//		}
//		User user = u.get();
//		Broker broker = user.getBroker();
//		
//		pageIndex = pageIndex - 1;
//		Pageable phantrang = PageRequest.of(pageIndex, 5, Sort.by("id"));
//		
//		List<Product> product = productRepository.findByBroker(broker, phantrang);
//		return new ResponseEntity<>(product, HttpStatus.OK);
//	}
	
	
//	@GetMapping(path = "/getAllPostPending/{pageIndex}")
//	public ResponseEntity<Object> getAllPostPending(Principal p, @PathVariable("pageIndex") int pageIndex, Pageable page) {
//		//String email = p.getName();
//		String email = "phuchuynhqn11@gmail.com";
//		Optional<User> u = userRepository.findByEmail(email);
//		if(!u.isPresent()) {
//			return new ResponseEntity<>("Not found correct user", HttpStatus.NOT_FOUND);
//		}
//		User user = u.get();
//		Broker broker = user.getBroker();
//		
//		pageIndex = pageIndex - 1;
//		Pageable phantrang = PageRequest.of(pageIndex, 5, Sort.by("id"));
//		
//		List<Product> product = productRepository.findByBrokerAndRequestStatus(broker, RequestState.PENDING, phantrang);
//		return new ResponseEntity<>(product, HttpStatus.OK);
//	}
	
//	@GetMapping(path = "/getAllPostApproved/{pageIndex}")
//	public ResponseEntity<Object> getAllPostApproved(Principal p, @PathVariable("pageIndex") int pageIndex, Pageable page) {
//		//String email = p.getName();
//		String email = "phuchuynhqn11@gmail.com";
//		Optional<User> u = userRepository.findByEmail(email);
//		if(!u.isPresent()) {
//			return new ResponseEntity<>("Not found correct user", HttpStatus.NOT_FOUND);
//		}
//		User user = u.get();
//		Broker broker = user.getBroker();
//		
//		pageIndex = pageIndex - 1;
//		Pageable phantrang = PageRequest.of(pageIndex, 5, Sort.by("id"));
//		
//		List<Product> product = productRepository.findByBrokerAndRequestStatus(broker, RequestState.APPROVED, phantrang);
//		return new ResponseEntity<>(product, HttpStatus.OK);
//	}
//	
	
	
//	@GetMapping(path = "/post/{id}")
//	public ResponseEntity<Object> getPostById(@PathVariable("id") long id, Principal p) {
//		//String email = p.getName();
//		String email = "phuchuynhqn11@gmail.com";
//		Optional<User> u = userRepository.findByEmail(email);
//		if(!u.isPresent()) {
//			return new ResponseEntity<>("Not found correct user", HttpStatus.NOT_FOUND);
//		}
//		User user = u.get();
//		Broker broker = user.getBroker();
//		List<Product> product = productRepository.findByBroker(broker);
//		for(Product pro: product) {
//			if(pro.getId() == id) {
//				return new ResponseEntity<>(pro, HttpStatus.OK);
//			}
//		}
////		Optional<Product> pro = productRepository.findById(id);
////		if(pro == null) {
////			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
////		}
////		return new ResponseEntity<>(pro.get(), HttpStatus.OK);
//		return new ResponseEntity<>("Not found post with its id", HttpStatus.NOT_FOUND);
//	}
	
	/**
	 * @summary Broker edit infor invividual of mine.
	 * @date  2019
	 * @author Tu Viet Van
	 * @param  
	 * @return 
	 **/
	@PutMapping(path = "/editBroker")
	public ResponseEntity<Object> editUser(@Valid @RequestBody BrokerDTOEdit brokerDTOEdit, BindingResult result) throws ParseException {
		
		System.out.println("xin chao edit");
		SimpleDateFormat format = new SimpleDateFormat(
				"yyy-MM-dd HH:mm:ss");
		String email = brokerDTOEdit.getEmail();
		User user = userService.findByEmail(email);
		if(user == null) {
			return new ResponseEntity<>("Khong ton tai user de sua", HttpStatus.NOT_FOUND);
		}
		String nameBroker = brokerDTOEdit.getNameBroker();
		Boolean gender = brokerDTOEdit.getGender();
		String phoneNumber = brokerDTOEdit.getPhoneNumber();
		String address = brokerDTOEdit.getAddress();
		Boolean active = brokerDTOEdit.getActive();
		Boolean nonDel = brokerDTOEdit.getNonDel();
		String password = brokerDTOEdit.getPassword();
		String dateOfBirth = brokerDTOEdit.getDateOfBirth();
		Date date = format.parse(dateOfBirth);
		String role = brokerDTOEdit.getRole();
		Broker broker = new Broker();
		//cai nay phan quyen sau...
		//User userObject = new User();
		//userObject.setEmail(email);
		user.setPassword(password);
		user.setActive(active);
		user.setNameUser(nameBroker);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setAddress(address);
		user.setDayOfBirth(date);
		user.setNonDel(nonDel);
//		Optional<Role> roleObject = roleRepository.findByRoleName(role);
//		if(roleObject.get() == null) {
//			return new ResponseEntity<>("Banj cap quyen sai cho Role", HttpStatus.NOT_FOUND);
//		}
		//Role roleO = roleObject.get();
		//roleRepository.save(roleO);
		//user.setRole(roleO);
		userRepository.save(user);
		//broker.setUser(user);
		//brokerRepository.save(broker);	
		return new ResponseEntity<>("Da sua broker thanh cong", HttpStatus.OK);	
	}
	
	
	@PutMapping(path = "/editDetail")
	public ResponseEntity<Object> editBroker(@Valid @RequestBody BrokerResponse brokerResponse, BindingResult result) throws ParseException {
		
		System.out.println("xin chao edit");
		SimpleDateFormat format = new SimpleDateFormat(
				"yyy-MM-dd HH:mm:ss");
		String email = brokerResponse.getEmail();
		String nameBroker = brokerResponse.getNameBroker();
		boolean active = brokerResponse.getActive();
		boolean nonDel = brokerResponse.getNonDel();
		Boolean gender = brokerResponse.getGender();
		String phoneNumber = brokerResponse.getPhoneNumber();
		String address = brokerResponse.getAddress();
		String dateOfBirth = brokerResponse.getDateOfBirth();
		Date date = format.parse(dateOfBirth);
		Optional<User> u = userRepository.findByEmail(email);
		if(!u.isPresent()) {
			return new ResponseEntity<>("Sai email cua broker de chinh sua...", HttpStatus.NOT_FOUND);
		}
		User user = u.get();
		user.setNameUser(nameBroker);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setAddress(address);
		user.setDayOfBirth(date);
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listOwnersWithBroker")
	public ResponseEntity<Object> listOwnersWithBroker(Principal p) {
		String email = p.getName();
		//String email = "abc@gmail.com";
		List<Owner> listOwners = ownerRepository.findByBrokerUserEmail(email);
		if((listOwners == null) || (listOwners.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listOwners, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/listOwnersWithBrokerAndTrangThai")
	public ResponseEntity<Object> listOwnersWithBrokerAndTrangThai(Principal p) {
		String email = p.getName();
		//String email = "abc@gmail.com";
		List<Owner> listOwners = ownerRepository.findByBrokerUserEmailAndTrangThai(email, false);
		if((listOwners == null) || (listOwners.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listOwners, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/detailOwner/{id}")
	public ResponseEntity<Object> detailOwner(@PathVariable("id") long id) {
		Optional<Owner> ownerOptional = ownerRepository.findById(id);
		if(!ownerOptional.isPresent()) {
			return new ResponseEntity<>("Không tồn tại owner có id này!!!", HttpStatus.NOT_FOUND);
		}
		Owner owner = ownerOptional.get();
		return new ResponseEntity<>(owner, HttpStatus.OK);
		
	}
	
	/**
	 * Broker them moi bai dang 
	 * 
	 */
//	@PostMapping("/uploadFile")
//    //UploadFileResponse
//    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("newPost") String newPost) throws JsonParseException, JsonMappingException, IOException {
//        DBFile dbFile = DBFileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(dbFile.getId().toString())
//                .toUriString();
//        
//        //sua tai day
//        //NewPostDTO a = new NewPostDTO();
//        ObjectMapper mapper = new ObjectMapper();
//        NewPostDTO newPostDTO = mapper.readValue(newPost, NewPostDTO.class);
//        
////        DBFile m = new DBFile();
////        m.setId(10L);
////        m.setFileName("ghghg.jpg");
//        dBFileRepository.save(dbFile);
        
        
	@PostMapping(path = "/addNewPostAndUploadFile")
	public ResponseEntity<Object> addUserAndUploadFile(@RequestParam("file") MultipartFile file, @RequestParam("newPost") String newPost) throws JsonParseException, JsonMappingException, IOException {
//		
//		String email = p.getName();
//		Optional<User> user = userRepository.findByEmail(email);
//		if(!user.isPresent()) {
//			return new ResponseEntity<>("Error This!", HttpStatus.OK);
//		}
//		
//	
//	    if(file == null) {
//	    	return new ResponseEntity<>("file la null", HttpStatus.OK);
//	    }
		
		DBFile dbFile = dBFileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/downloadFile/")
              .path(dbFile.getId().toString())
              .toUriString();
				
		//NewPostDTO newPostDTO = new NewPostDTO();
      ObjectMapper mapper = new ObjectMapper();
      NewPostDTO newPostDTO = mapper.readValue(newPost, NewPostDTO.class);
      dBFileRepository.save(dbFile);
		
		Product product = new Product();
		product.setdBFile(dbFile);
		//Boolean productStatus = newPostDTO.getProductStatus();
		String productName = newPostDTO.getProductName();
		
		
		String productLocation = newPostDTO.getProductLocation();
		Optional<Ward> wardOptional = wardRepository.findByMaWard(productLocation);
		Ward ward = wardOptional.get();
		District district = ward.getDistrict();
		City city = district.getCity();
		
		
		
		Long productCost = newPostDTO.getProductCost();
		//String productImage = newPostDTO.getProductImage();
		Long productAcreage = newPostDTO.getProductAcreage();
		String productDescription = newPostDTO.getProductDescription();
		//String productLocationCuthe = productDescription + ", " + city.getNameCity() + ", " + district.getNameDistrict() + ", " + ward.getNameWard();
		String productLocationCuthe = productDescription + ", " + ward.getNameWard() + ", " + district.getNameDistrict() + ", " + city.getNameCity();
		String zipCode = newPostDTO.getZipCode();
		//String requestStatus = newPostDTO.getRequestStatus();
		
		product.setProductStatus(false);
		product.setProductName(productName);
		product.setProductLocation(productLocationCuthe);
		product.setProductCost(productCost);
		//product.setProductImage("anh1");
		product.setProductAcreage(productAcreage);
		product.setProductDescription(productDescription);
		product.setRequestStatus(RequestState.PENDING);
		product.setZipCode(zipCode);
		
		//String email = p.getName();
		
		//Long brokerId = newPostDTO.getBrokerId();
//		Optional<User> u = userRepository.findByEmail(email);
//		if(!u.isPresent()) {
//			return new ResponseEntity<>("Error User", HttpStatus.NOT_FOUND);
//		}
//		User user = u.get();
//		Broker broObject = user.getBroker();
		//product.setBroker(broObject);
		
		
		Long currencyId = newPostDTO.getCurrencyId();
		Optional<Currency> cur = currencyRepository.findById(currencyId);
		if(!cur.isPresent()) {
			return new ResponseEntity<>("Error Currency", HttpStatus.NOT_FOUND);
		}
		Currency curObject = cur.get();
		product.setCurrency(curObject);
		
		Long catalogDetailId = newPostDTO.getCatalogDetailId();
		
		Optional<CatalogDetail> catalogDetail = catalogDetailRepository.findById(catalogDetailId);
		if(!catalogDetail.isPresent()) {
			return new ResponseEntity<>("Error Catalog Detail", HttpStatus.NOT_FOUND);
		}
		CatalogDetail catalogDetailObject = catalogDetail.get();
		product.setCatalogDetail(catalogDetailObject);
		
		Long ownerId = newPostDTO.getOwnerId();
		
		
		
		Optional<Owner> own = ownerRepository.findById(ownerId);
		if(!own.isPresent()) {
			return new ResponseEntity<>("Error Owner", HttpStatus.NOT_FOUND);
		}
		Owner ownObject = own.get();
		ownObject.setTrangThai(true);
		product.setOwner(ownObject);
		
		productRepository.save(product);
	
		return new ResponseEntity<>("Da them bai dang thanh cong", HttpStatus.OK);
	}
	
	@PutMapping(path = "/editProfileBroker")
	public ResponseEntity<Object> editProfileBroker(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("brokerObject") String brokerObject, Principal p) throws JsonParseException, JsonMappingException, IOException, ParseException {
		
		String email = p.getName();
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(!userOptional.isPresent()) {
			return new ResponseEntity<>("Sai phan quyen cua admin roi!!!", HttpStatus.OK);
		}
		User user = userOptional.get();
		
			ObjectMapper mapper = new ObjectMapper();
			EditBrokerDTO brokerDTO = mapper.readValue(brokerObject, EditBrokerDTO.class);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd");
			Date date = format.parse(brokerDTO.getDateOfBirth());
			
		    //hoten,sodienthoai,ngaysinh,gioitinh,diachi
			user.setNameUser(brokerDTO.getNameBroker());
			user.setPhoneNumber(brokerDTO.getPhoneNumber());
			user.setDayOfBirth(date);
			user.setGender(brokerDTO.getGender());
			user.setAddress(brokerDTO.getAddress());
			
			if(file != null) {
				
				DBFileProfile dbFile = dBFileProfileService.storeFile(file);
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		              .path("/downloadFile/")
		              .path(dbFile.getId().toString())
		              .toUriString();
						
				//NewPostDTO newPostDTO = new NewPostDTO();
		      dBFileProfileRepository.save(dbFile);
		      user.setdBFileProfile(dbFile);
			}
			userRepository.save(user);
		
		return new ResponseEntity<>("Ban da sua thong tin ca nhan thanh cong!!!", HttpStatus.OK);	
	
	}
	
	
	
	
	
	
	@PutMapping(path = "/editPostBrokerWithImage")
	public ResponseEntity<Object> editPostBrokerWithImage(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("postObject") String postObject) throws JsonParseException, JsonMappingException, IOException, ParseException {
		
//		String email = p.getName();
//		Optional<User> userOptional = userRepository.findByEmail(email);
//		if(!userOptional.isPresent()) {
//			return new ResponseEntity<>("Sai phan quyen cua admin roi!!!", HttpStatus.OK);
//		}
//		User user = userOptional.get();
		
			ObjectMapper mapper = new ObjectMapper();
			EditPostBrokerDTO editPostBrokerDTO = mapper.readValue(postObject, EditPostBrokerDTO.class);
//			SimpleDateFormat format = new SimpleDateFormat(
//					"yyy-MM-dd");
//			Date date = format.parse(brokerDTO.getDateOfBirth());
			Long id = editPostBrokerDTO.getId();
			Optional<Product> productOptional = productRepository.findById(id);
			if(!productOptional.isPresent()) {
				return new ResponseEntity<>("Error product in system!!!", HttpStatus.OK);
			}
			Product product = productOptional.get();
			
			String productName = editPostBrokerDTO.getProductName();
			String productLocation = editPostBrokerDTO.getProductLocation();
			Long productCost = editPostBrokerDTO.getProductCost();
			Long productAcreage = editPostBrokerDTO.getProductAcreage();
			product.setProductName(productName);
			product.setProductLocation(productLocation);
			product.setProductCost(productCost);
			product.setProductAcreage(productAcreage);
			if(file != null) {
				DBFile dbFile = dBFileStorageService.storeFile(file);
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		              .path("/downloadFile/")
		              .path(dbFile.getId().toString())
		              .toUriString();
						
				//NewPostDTO newPostDTO = new NewPostDTO();
		      dBFileRepository.save(dbFile);
		      product.setdBFile(dbFile);
			}
			productRepository.save(product);	
		return new ResponseEntity<>("Bạn đã sửa thông tin lô đất thành công!!!", HttpStatus.OK);	
	
	}
	
	    @GetMapping(path = "/listCustomers")
		public ResponseEntity<Object> listCustomers(Principal p) {
		
			String email = p.getName();
			Optional<User> userOptional = userRepository.findByEmail(email);
			if(!userOptional.isPresent()) {
				return new ResponseEntity<>("Sai user roi!!!", HttpStatus.NOT_FOUND);
			}
			User u = userOptional.get();
			Broker broker = u.getBroker();
			List<Product> productOptional = productRepository.findByBroker(broker);
			if((productOptional == null) || (productOptional.size() == 0)) {
				return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
			}
			List<CustomerNotAuthority> listCus = new ArrayList<>();
			CustomerNotAuthority customerNotAuthority;
			for(Product pro: productOptional) {
				customerNotAuthority = pro.getCustomerNotAuthority();
			   
				listCus.add(customerNotAuthority);
				
			}
			return new ResponseEntity<>(listCus, HttpStatus.OK);
		}
	    
	    @GetMapping(path = "/customers/{id}")
	    public ResponseEntity<Object> detailCustomer(@PathVariable("id") long id) {
	    	
	    	Optional<CustomerNotAuthority> cusOptional = customerNotAuthorityRepository.findById(id);
	    	if(!cusOptional.isPresent()) {
	    		return new ResponseEntity<>("Error customer in system!!!", HttpStatus.NOT_FOUND);
	    	}
	    	CustomerNotAuthority cus = cusOptional.get();
	    	return new ResponseEntity<>(cus, HttpStatus.OK);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	@PutMapping(path = "/EditPostAndUploadFile/{id}")
	public ResponseEntity<Object> editPostAndUploadFile(@PathVariable("id") long id, @RequestParam("file") MultipartFile file, @RequestParam("newPost") String newPost) throws JsonParseException, JsonMappingException, IOException {
		
		Optional<Product> productOptional = productRepository.findById(id);
		if(!productOptional.isPresent()) {
			return new ResponseEntity<>("Error id roi broker oi...", HttpStatus.NOT_FOUND);
		}
		Product product = productOptional.get();
		
		DBFile dbFile = dBFileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/downloadFile/")
              .path(dbFile.getId().toString())
              .toUriString();
				
		//NewPostDTO newPostDTO = new NewPostDTO();
      ObjectMapper mapper = new ObjectMapper();
      NewPostDTO newPostDTO = mapper.readValue(newPost, NewPostDTO.class);
      dBFileRepository.save(dbFile);
      product.setdBFile(dbFile);
      
		
		
		//Boolean productStatus = newPostDTO.getProductStatus();
		String productName = newPostDTO.getProductName();
		String productLocation = newPostDTO.getProductLocation();
		Long productCost = newPostDTO.getProductCost();
		//String productImage = newPostDTO.getProductImage();
		Long productAcreage = newPostDTO.getProductAcreage();
		String productDescription = newPostDTO.getProductDescription();
		String zipCode = newPostDTO.getZipCode();
		//String requestStatus = newPostDTO.getRequestStatus();
		
		product.setProductStatus(false);
		product.setProductName(productName);
		product.setProductLocation(productLocation);
		product.setProductCost(productCost);
		//product.setProductImage(productImage);
		product.setProductAcreage(productAcreage);
		product.setProductDescription(productDescription);
		product.setRequestStatus(RequestState.PENDING);
		product.setZipCode(zipCode);
		
		//String email = p.getName();
		
		//Long brokerId = newPostDTO.getBrokerId();
//		Optional<User> u = userRepository.findByEmail(email);
//		if(!u.isPresent()) {
//			return new ResponseEntity<>("Error User", HttpStatus.NOT_FOUND);
//		}
//		User user = u.get();
//		Broker broObject = user.getBroker();
		//product.setBroker(broObject);
		
		
		Long currencyId = newPostDTO.getCurrencyId();
		Optional<Currency> cur = currencyRepository.findById(currencyId);
		if(!cur.isPresent()) {
			return new ResponseEntity<>("Error Currency", HttpStatus.NOT_FOUND);
		}
		Currency curObject = cur.get();
		product.setCurrency(curObject);
		
		Long catalogDetailId = newPostDTO.getCatalogDetailId();
		
		Optional<CatalogDetail> catalogDetail = catalogDetailRepository.findById(catalogDetailId);
		if(!catalogDetail.isPresent()) {
			return new ResponseEntity<>("Error Catalog Detail", HttpStatus.NOT_FOUND);
		}
		CatalogDetail catalogDetailObject = catalogDetail.get();
		product.setCatalogDetail(catalogDetailObject);
		
		Long ownerId = newPostDTO.getOwnerId();
		Optional<Owner> own = ownerRepository.findById(ownerId);
		if(!own.isPresent()) {
			return new ResponseEntity<>("Error Owner", HttpStatus.NOT_FOUND);
		}
		Owner ownObject = own.get();
		ownObject.setTrangThai(true);
		product.setOwner(ownObject);
		
		productRepository.save(product);
	
		return new ResponseEntity<>("Da sua bai dang thanh cong", HttpStatus.OK);
	}
	
	
	
	/**
	 * Edit Post
	 */
	
	@PutMapping(path = "/editPostPresent")
	public ResponseEntity<Object> editUser(@Valid @RequestBody NewPostDTO newPostDTO, BindingResult result) throws ParseException {
		
		//Product product = new Product();
		long id = newPostDTO.getId();
		Optional<Product> p = productRepository.findById(id);
		if(!p.isPresent()) {
			return new ResponseEntity<>("Error with post present...", HttpStatus.NOT_FOUND);
		}
		Product product = p.get();
		Boolean productStatus = newPostDTO.getProductStatus();
		String productName = newPostDTO.getProductName();
		String productLocation = newPostDTO.getProductLocation();
		Long productCost = newPostDTO.getProductCost();
		String productImage = newPostDTO.getProductImage();
		Long productAcreage = newPostDTO.getProductAcreage();
		String productDescription = newPostDTO.getProductDescription();
		String requestStatus = newPostDTO.getRequestStatus();
		
		product.setProductStatus(productStatus);
		product.setProductName(productName);
		product.setProductLocation(productLocation);
		product.setProductCost(productCost);
		product.setProductImage(productImage);
		product.setProductAcreage(productAcreage);
		product.setProductDescription(productDescription);
		product.setRequestStatus(requestStatus);
		
		
//		Long brokerId = newPostDTO.getBrokerId();
//		Optional<Broker> bro = brokerRepository.findById(brokerId);
//		if(!bro.isPresent()) {
//			return new ResponseEntity<>("Error Broker", HttpStatus.NOT_FOUND);
//		}
//		Broker broObject = bro.get();
		//product.setBroker(broObject);
		
		
		Long currencyId = newPostDTO.getCurrencyId();
		Optional<Currency> cur = currencyRepository.findById(currencyId);
		if(!cur.isPresent()) {
			return new ResponseEntity<>("Error Currency", HttpStatus.NOT_FOUND);
		}
		Currency curObject = cur.get();
		product.setCurrency(curObject);
		
		Long ownerId = newPostDTO.getOwnerId();
		Optional<Owner> own = ownerRepository.findById(ownerId);
		if(!own.isPresent()) {
			return new ResponseEntity<>("Error Owner", HttpStatus.NOT_FOUND);
		}
		Owner ownObject = own.get();
		product.setOwner(ownObject);
		
		Long catalogDetailId = newPostDTO.getCatalogDetailId();
		
		Optional<CatalogDetail> catalogDetail = catalogDetailRepository.findById(catalogDetailId);
		if(!catalogDetail.isPresent()) {
			return new ResponseEntity<>("Error Catalog Detail", HttpStatus.NOT_FOUND);
		}
		CatalogDetail catalogDetailObject = catalogDetail.get();
		product.setCatalogDetail(catalogDetailObject);
		Product pro = productRepository.save(product);
	    return new ResponseEntity<>(pro, HttpStatus.OK);
		
		//return new ResponseEntity<>("Da sua bai dang thanh cong", HttpStatus.OK);
	}
	
	/**
	 * Delete post
	 */
	@DeleteMapping(path = "/post/{id}")
	 //@RequestMapping(value = "/deletePost/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delPost(@PathVariable("id") long id) {
		Optional<Product> pro = productRepository.findById(id);
		if(!pro.isPresent()) {
			return new ResponseEntity<>("Khong ton tai bai dang nay...", HttpStatus.NOT_FOUND);
		}
		Product proObject = pro.get();
		proObject.setNonDel(false);
		productRepository.save(proObject);
		return new ResponseEntity<>("Da xoa thanh cong", HttpStatus.OK);
	}
	
	@GetMapping(path = "/post/{id}")
	 //@RequestMapping(value = "/deletePost/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> getPost(@PathVariable("id") long id) {
		Optional<Product> pro = productRepository.findById(id);
		if(!pro.isPresent()) {
			return new ResponseEntity<>("Khong ton tai bai dang nay...", HttpStatus.NOT_FOUND);
		}
		Product proObject = pro.get();
		ProductPublicResponse ProductPublicResponse = productService.convertToProductResponsePublic(proObject);
		return new ResponseEntity<>(ProductPublicResponse, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/detail-change-password")
	public ResponseEntity<Object> toDoChangePasswordDetail(@Valid @RequestBody PasswordDTO passwordDTO,
			BindingResult bindingResult, Principal p) { 
	    String email = p.getName();
		//String email = "tuvietvancntt@gmail.com";
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
	@GetMapping(path = "/listPostApproved/{pageIndex}")
	public ResponseEntity<Object> listPostApproved(Principal p, @PathVariable("pageIndex") int pageIndex, Pageable page) {
		String email = p.getName();
		//String email = "DangVanTri@gmail.com";
		Optional<User> userOptional = userRepository.findByEmail(email);
		User u = userOptional.get();
		Broker broker = u.getBroker();
		
		pageIndex = pageIndex - 1;
		Pageable phantrang = PageRequest.of(pageIndex, 5, Sort.by("id"));
		
		//List<Product> listProduct = productRepository.findByBrokerUserEmailAndNonDelAndRequestStatus(email, true, RequestState.APPROVED, phantrang);
		
		List<Product> listProduct = productRepository.findByOwnerBrokerAndNonDelAndRequestStatus(broker, true, RequestState.APPROVED, phantrang);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse ppr;
		for(Product pro: listProduct) {
			ppr = productService.convertToProductResponsePublic(pro);
			list.add(ppr);
			
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
		@GetMapping(path = "/getNumberOfPostApproved")
		public ResponseEntity<Object> displayNumberOfListPostApproved(Principal p) {
			
			String email = p.getName();
			
			//List<Broker> listBroker = brokerRepository.findAll();
			
			List<Product> listProduct = productRepository.findByOwnerBrokerUserEmailAndNonDelAndRequestStatus(email, true, RequestState.APPROVED);
			if((listProduct == null) || (listProduct.size() == 0)) {
				return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
			}
			if(listProduct.size() % 5 == 0) {
				return new ResponseEntity<>(listProduct.size()/5, HttpStatus.OK);
			}
			
			return new ResponseEntity<>((listProduct.size()/5 + 1), HttpStatus.OK);
			
		}
	
		@GetMapping(path = "/getNumberOfPostPending")
		public ResponseEntity<Object> displayNumberOfListPostPending(Principal p) {
			
			String email = p.getName();
			
			//List<Broker> listBroker = brokerRepository.findAll();
			
			List<Product> listProduct = productRepository.findByOwnerBrokerUserEmailAndNonDelAndRequestStatus(email, true, RequestState.PENDING);
			if((listProduct == null) || (listProduct.size() == 0)) {
				return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
			}
			if(listProduct.size() % 5 == 0) {
				return new ResponseEntity<>(listProduct.size()/5, HttpStatus.OK);
			}
			
			return new ResponseEntity<>((listProduct.size()/5 + 1), HttpStatus.OK);
			
		}
	
	
	
	
	
	
	
	
	
	
	@GetMapping(path = "/listPostApproved")
	public ResponseEntity<Object> listPostApprovedAbdroid(Principal p) {
		String email = p.getName();
		//String email = "DangVanTri@gmail.com";
		Optional<User> userOptional = userRepository.findByEmail(email);
		User u = userOptional.get();
		Broker broker = u.getBroker();
		
		List<Product> listProduct = productRepository.findByOwnerBrokerAndNonDelAndRequestStatus(broker, true, RequestState.APPROVED);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse ppr;
		for(Product pro: listProduct) {
			ppr = productService.convertToProductResponsePublic(pro);
			list.add(ppr);
			
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listPostPending/{pageIndex}")
	public ResponseEntity<Object> listPostPending(Principal p, @PathVariable("pageIndex") int pageIndex, Pageable page) {
		String email = p.getName();
		//String email = "DangVanTri@gmail.com";
		Optional<User> userOptional = userRepository.findByEmail(email);
		User u = userOptional.get();
		Broker broker = u.getBroker();
		
		pageIndex = pageIndex - 1;
		Pageable phantrang = PageRequest.of(pageIndex, 5, Sort.by("id"));
		
//		List<Product> listProduct = productRepository.findByBrokerUserEmailAndNonDelAndRequestStatus(email, true, RequestState.PENDING, phantrang);
		List<Product> listProduct = productRepository.findByOwnerBrokerAndNonDelAndRequestStatus(broker, true, RequestState.PENDING, phantrang);
		
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse ppr;
		for(Product pro: listProduct) {
			ppr = productService.convertToProductResponsePublic(pro);
			list.add(ppr);
			
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listPostPending")
	public ResponseEntity<Object> listPostPending(Principal p) {
		
		String email = p.getName();
		//String email = "DangVanTri@gmail.com";
		Optional<User> userOptional = userRepository.findByEmail(email);
		User u = userOptional.get();
		Broker broker = u.getBroker();	
		
		List<Product> listProduct = productRepository.findByOwnerBrokerAndNonDelAndRequestStatus(broker, true, RequestState.PENDING);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse ppr;
		for(Product pro: listProduct) {
			ppr = productService.convertToProductResponsePublic(pro);
			list.add(ppr);
			
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "searchPublic/undefined?zipcode=undefined")
	public ResponseEntity<Object> listPostApprovedPublic(Principal p) {
		
		//String email = p.getName();
				String email = "phuchuynhqn11y@gmail.com";
				List<Product> listProduct = productRepository.findByBrokerUserEmailAndRequestStatus(email, RequestState.APPROVED);
				if((listProduct == null) || (listProduct.size() == 0)) {
					return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
				}
				List<ProductPublicResponse> list = new ArrayList<>();
				ProductPublicResponse ppr;
				for(Product pro: listProduct) {
					ppr = productService.convertToProductResponsePublic(pro);
					list.add(ppr);
					
				}
				return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("/change-password-profile")
	public ResponseEntity<Object> ChangePassword(@Valid @RequestBody PasswordDTOProfile passwordDTOProfile,
			BindingResult bindingResult, Principal p) throws JsonProcessingException {
		String email = p.getName();
		//String email = "huynhtruongphuc@gmail.com";
		Optional<User> u = userRepository.findByEmail(email);
		if(!u.isPresent()) {
			return new ResponseEntity<>("User not found....", HttpStatus.OK);
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
	
	
	@GetMapping(path = "/listCurrencys")
	public ResponseEntity<Object> listCurrencys() {
		List<Currency> list = currencyRepository.findAll();
		if((list == null) || (list.size() == 0)) {
			return new ResponseEntity<>("List currencys is empty...", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	@GetMapping(path = "/listCatalog")
	public ResponseEntity<Object> listCatalog() {
		List<Catalog> listCatalog = catalogRepository.findAll();
		if(listCatalog == null || listCatalog.size() == 0) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listCatalog, HttpStatus.OK);
	}
	
	@GetMapping(path = "/catalog/{id}")
	public ResponseEntity<Object> listCatalogDetail(@PathVariable("id") Long id) {
		List<CatalogDetail> listCatalogDetail = catalogDetailRepository.findByCatalogId(id);
		if(listCatalogDetail == null || listCatalogDetail.size() == 0) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listCatalogDetail, HttpStatus.OK);
	}

	
	@GetMapping(path = "/getAllPostWithBroker")
	public ResponseEntity<Object> getAllPostWithBroker(Principal p) {
		
		String email = p.getName();
		//findByOwnerBrokerAndNonDelAndRequestStatus
		List<Product> listProduct = productRepository.findByBrokerUserEmailAndProductStatusAndNonDelAndRequestStatus(email, true, true, RequestState.APPROVED);
		if((listProduct == null)||(listProduct.size() == 0)) {
			return new ResponseEntity<>("Not found any broker this security!!!", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse pro;
		for(Product product: listProduct) {
			pro = productService.convertToProductResponsePublic(product);
			list.add(pro);
		}	
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	//danh sach nguoi moi gioi
//	@GetMapping(path = "/list-broker")
//	public ResponseEntity<Object> viewListBroker() {
//		List<Broker> listBroker = brokerRepository.findAll();
//		if(listBroker.size() == 0) {
//			return new ResponseEntity<>("Danh sach nguoi moi gioi rong", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(listBroker, HttpStatus.OK);
//	}
//	
//	//thong tin chi tiet nguoi moi gia...
//	@GetMapping("broker/{id}")
//	public ResponseEntity<Object> detailBroker(@PathVariable("id") long id) {
//		Broker broker = brokerRepository.getOne(id);
//		if(broker == null) {
//			return new ResponseEntity<>("kHONG TIM THAY BROKER THEO ID", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(broker, HttpStatus.OK);
//	}
//	
//	//sua thong ti nguoi moi gioi
//	@PutMapping(path = "/editBroker")
//	public ResponseEntity<Object> editBroker(@RequestBody BrokerDTO brokerDTO) {
//	    System.out.println(brokerDTO);
//	    String email = brokerDTO.getEmail();
//	   // Optional<Broker> broker = brokerRepository.findUser
//	    Broker broker = brokerRepository.findByUserEmail(email);
//	    User user =  broker.getUser();
//	    //user.setDescription(brokerDTO.getDescription());
//	    broker.setUser(user);
//	    brokerRepository.save(broker);
//	    
////	    if(listBroker.size() == 0) {
////	    	return new ResponseEntity<>("sai email", HttpStatus.NOT_FOUND);
////	    }
//	    return new ResponseEntity<>(broker, HttpStatus.OK);
//	}
}
