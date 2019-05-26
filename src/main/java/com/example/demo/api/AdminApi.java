package com.example.demo.api;

import java.security.Principal;
import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.QueryParam;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Broker;
import com.example.demo.entity.Catalog;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerNotAuthority;
import com.example.demo.entity.Owner;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.AssignBrokerWithCustomerDTO;
import com.example.demo.entity.dto.AssignBrokerWithOldCustomerDTO;
import com.example.demo.entity.dto.BrokerDTO;
import com.example.demo.entity.dto.BrokerDTOEdit;
import com.example.demo.entity.dto.CustomerDTO;
import com.example.demo.entity.dto.CustomerDTOEdit;
import com.example.demo.entity.dto.PasswordDTO;
import com.example.demo.entity.dto.PasswordDTOProfile;
import com.example.demo.entity.dto.ProductDTO;
import com.example.demo.entity.dto.TestThuDTO;
import com.example.demo.entity.response.AdminResponse;
import com.example.demo.entity.response.AssignBrokerWithCustomerDTOResponse;
import com.example.demo.entity.response.BrokerResponse;
import com.example.demo.entity.response.CustomerNotAuthorityResponse;
import com.example.demo.entity.response.CustomerResponse;
import com.example.demo.entity.response.FinalProductAdmin;
import com.example.demo.entity.response.PasswordDTOProfileResponse;
import com.example.demo.entity.response.ProductPublicResponse;
import com.example.demo.entity.response.ResponseData;
import com.example.demo.entity.response.SignupDTOResponse;
import com.example.demo.entity.response.UserSearchResponse;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.BrokerRepository;
import com.example.demo.repository.CatalogRepository;
import com.example.demo.repository.CustomerNotAuthorityRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.BrokerService;
import com.example.demo.service.CustomerNotAuthorityService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.PasswordService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.util.RequestState;
import com.example.demo.util.RoleConst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Transactional
@RequestMapping("/a2/admin")
public class AdminApi {
	
	@Autowired
	private BrokerService brokerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BrokerRepository brokerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerNotAuthorityRepository customerNotAuthorityRepository;
	
	@Autowired
	private CustomerNotAuthorityService customerNotAuthorityService;
	
	@Autowired
	private AdminService adminService;
	
//	@Autowired
//	private Broker1Service broker1Service;
//	
//	@Autowired
//	private Broker1Repository broker1Repository;
//	
	
	
	
	
	
	@PostMapping(path = "/seekBroker")
	public ResponseEntity<Object> handleSeek(@RequestBody QueryParam<UserSearchResponse> page) {
		ResponseData res = new ResponseData();
		try {
			//List<UserSearchResponse> data = broker1Repository.seek(page);
			List<UserSearchResponse> data = null;
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
	
	@GetMapping(path = "/profileAdmin")
		public ResponseEntity<Object> profileAdmin(Principal p) {
			//String email = p.getName();
		    String email = "abc@gmail.com";
			Admin admin = adminRepository.findByUserEmail(email);
			if(admin == null) {
				return new ResponseEntity<>("Not Found This Broker", HttpStatus.NOT_FOUND);
				
			}
			AdminResponse adminRes = adminService.convertToResponse(admin);
			if(adminRes == null) {
				return new ResponseEntity<>("Error Display Information this Broker", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(adminRes, HttpStatus.OK);	
		}
	
	
	
	
	/**
	 * @summary Admin manage list of all Broker in system
	 * @date sep 12, 2019
	 * @author Tu Viet Van
	 * @param  none
	 * @return listBroker
	 * @throws ParseException 
	 **/
	@GetMapping(path = "/listBroker/{pageIndex}")
	public ResponseEntity<Object> displayListBroker(@PathVariable("pageIndex") int pageIndex, Pageable page) throws ParseException {
		pageIndex = pageIndex - 1;
//		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 2, Sort.by("id").descending());
		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 5, Sort.by("id"));
		//List<Broker> listBroker = brokerService.findAll(firstPageWithTwoElements);
		List<Broker> listBroker = brokerService.findByNonDel(true, firstPageWithTwoElements);
		if((listBroker.size() == 0) || (listBroker == null) ) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		List<BrokerResponse> listBroRes = new ArrayList<>();
		BrokerResponse broRes = null;
		for(Broker bro: listBroker) {
			broRes = brokerService.convertToResponse(bro);
			if(broRes == null) {
				return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
			}
			listBroRes.add(broRes);
		}
		
		if(listBroRes == null) {
			return new ResponseEntity<>("List all broker are empty", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listBroRes, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(path = "/listBroker")
	public ResponseEntity<Object> displayListAllBroker() throws ParseException {
//		pageIndex = pageIndex - 1;
//		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 2, Sort.by("id").descending());
//		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 5, Sort.by("id"));
		//List<Broker> listBroker = brokerService.findAll(firstPageWithTwoElements);
		//List<Broker> listBroker = brokerService.findByNonDel(true, firstPageWithTwoElements);
		List<Broker> listBroker = brokerRepository.findByUserNonDel(true);
		if((listBroker.size() == 0) || (listBroker == null) ) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		List<BrokerResponse> listBroRes = new ArrayList<>();
		BrokerResponse broRes = null;
		for(Broker bro: listBroker) {
			broRes = brokerService.convertToResponse(bro);
			if(broRes == null) {
				return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
			}
			listBroRes.add(broRes);
		}
		
		if(listBroRes == null) {
			return new ResponseEntity<>("List all broker are empty", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listBroRes, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//lay tat ca cac number cua broker
	@GetMapping(path = "/getNumberOfBrokers")
	public ResponseEntity<Object> displayListBroker() {
		
		//List<Broker> listBroker = brokerRepository.findAll();
		
		List<Broker> listBroker = brokerService.findByNonDel(true);
		if((listBroker == null) || (listBroker.size() == 0)) {
			return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
		}
		if(listBroker.size() % 5 == 0) {
			return new ResponseEntity<>(listBroker.size()/5, HttpStatus.OK);
		}
		
		return new ResponseEntity<>((listBroker.size()/5 + 1), HttpStatus.OK);
		
	}
	
	//lay tat ca cac number cua customer
		@GetMapping(path = "/getNumberOfCustomers")
		public ResponseEntity<Object> displayListCustomer() {
			
			List<CustomerNotAuthority> listCustomerNotAuthority = customerNotAuthorityRepository.findByNonDel(true);
			if((listCustomerNotAuthority == null) || (listCustomerNotAuthority.size() == 0)) {
				return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
			}
			
			if(listCustomerNotAuthority.size() % 5 == 0) {
				return new ResponseEntity<>(listCustomerNotAuthority.size()/5, HttpStatus.OK);
			}
			
			return new ResponseEntity<>((listCustomerNotAuthority.size()/5 + 1), HttpStatus.OK);
		}
		
	
	@GetMapping(path = "/listCustomers/{pageIndex}")
	public ResponseEntity<Object> displayCustomers(@PathVariable("pageIndex") int pageIndex, Pageable page) throws ParseException {
		pageIndex = pageIndex - 1;
//		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 2, Sort.by("id").descending());
		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 5, Sort.by("id"));
		//List<Broker> listBroker = brokerService.findAll(firstPageWithTwoElements);
		
//		Page<Broker> p = brokerRepository.findByUserNonDel(b,firstPageWithTwoElements);
//		List<Broker> list = p.getContent();
//		
//		List<CustomerNotAuthority> listCus = customerNotAuthorityService.findAll(firstPageWithTwoElements);
		List<CustomerNotAuthority> listCus = customerNotAuthorityService.findByNonDel(true, firstPageWithTwoElements);
//		Page<CustomerNotAuthority> listCustomer = customerNotAuthorityRepository.findAll(firstPageWithTwoElements);
//		List<CustomerNotAuthority> listCus = listCustomer.getContent();
		if((listCus.size() == 0) || (listCus == null)) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		List<CustomerNotAuthorityResponse> list= new ArrayList<>();
		CustomerNotAuthorityResponse cusRes;
		for(CustomerNotAuthority cus: listCus) {
			cusRes = customerNotAuthorityService.convertToCusRes(cus);
			if(cusRes == null) {
				return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
			}
			list.add(cusRes);			
		}
		if((list.size() == 0) || (list == null)) {
			return new ResponseEntity<>("List Customer is empty", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	/**
	 * @Summary Admin manage detail of Broker
	 * @date git 
	 * @author Tu Viet Van
	 * @throws ParseException 
	 * @Param id
	 * @Return Broker object
	 **/
	@GetMapping(path = "/broker/{id}")
	public ResponseEntity<Object> detailBroker(@PathVariable("id") long id) throws ParseException {
	    //Broker broker = brokerService.getBrokerById(id);
		Broker broker = brokerRepository.getOne(id);
		if(broker == null) {
			return new ResponseEntity<>("NOT FOUND THIS BROKER", HttpStatus.NOT_FOUND);
		}
	    BrokerResponse broRes = brokerService.convertToResponse(broker);
	    if(broRes == null) {
	    	return new ResponseEntity<Object>("Not Found Broker by specific id", HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(broRes, HttpStatus.OK);
	}
	
	/**
	 * @Summary Admin manage add Broker
	 * @date 
	 * @author Tu Viet Van
	 * @throws ParseException 
	 * @Param 
	 * @Return 
	 **/
	@PostMapping(path = "/addBroker")
	public ResponseEntity<Object> addUser(@Valid @RequestBody BrokerDTO brokerDTO, BindingResult result) throws ParseException {
//		if(2 != 1) {
//		return new ResponseEntity<>("test thu", HttpStatus.OK);
//		}
		System.out.println("xin chao add");
//		SimpleDateFormat format = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
		
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
		
		String email = brokerDTO.getEmail();
		if(email == null) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		User user = userService.findByEmail(email);
		if(user != null) {
			return new ResponseEntity<>("User nay da ton tai", HttpStatus.NOT_FOUND);
		}		
		
		String nameBroker = brokerDTO.getNameBroker();
		Boolean gender = brokerDTO.getGender();
		String phoneNumber = brokerDTO.getPhoneNumber();
		String address = brokerDTO.getAddress();
		Boolean active = brokerDTO.getActive();
		String password = brokerDTO.getPassword();
		String dateOfBirth = brokerDTO.getDateOfBirth();
		Boolean nonDel = brokerDTO.getNonDel();
		Boolean nonBlock = brokerDTO.getNonBlock();
		String description = brokerDTO.getDescription();
		Date date = format.parse(dateOfBirth);
		
		//return new ResponseEntity<>(nameBroker + " " + gender + " " + phoneNumber + " " + address + " " + active + " " + password + " " + dateOfBirth + " ", HttpStatus.OK);
		////String role = brokerDTO.getRole();
		//String role = brokerDTO.getRole();
		Broker broker = new Broker();
//		////cai nay phan quyen sau...
		User userObject = new User();
		//userObject.setId(20L);
		userObject.setEmail(email);
		userObject.setPassword(password);
		userObject.setNonDel(nonDel);
		userObject.setNonLocked(nonBlock);
		userObject.setActive(active);
		userObject.setNameUser(nameBroker);
		userObject.setGender(gender);
		userObject.setPhoneNumber(phoneNumber);
		userObject.setAddress(address);
		userObject.setDayOfBirth(date);
		userObject.setDescription(description);
		
		
//		if(!role.equals(RoleConst.BROKER)) {
//			return new ResponseEntity<>("Set role error", HttpStatus.NOT_FOUND);
//		}
		Optional<Role> roleObject = roleRepository.findByRoleName(RoleConst.BROKER);
//		//return new ResponseEntity<>(roleObject, HttpStatus.OK);
		if(!roleObject.isPresent()) {
			return new ResponseEntity<>("Ban cap quyen sai cho Role", HttpStatus.NOT_FOUND);
		}
		Role ro = roleObject.get();
//		Long roleId = roleO.getId();
//		Role ro = new Role();
//		ro.setRoleDesc("kkk");
//		ro.setRoleName("kkk");
//		
		//roleRepository.save(ro);
		//userRepository.addUser(active, address, dateOfBirth, description, email, gender, nameBroker, nonDel, nonBlock, password, phoneNumber,a.getId());
		userObject.setRole(ro);
//		userObject.setId(20L);
		
//		Role role1 = new Role();
//		role1.setId(10L);
//		role1.setRoleDesc("kkkkk");
		
//		role1.setRoleName("hhghghg");
//		
		//roleRepository.save(ro);
		
		
		
		//return new ResponseEntity<>(userObject, HttpStatus.OK);
		userRepository.save(userObject);
		
		//userRepository.save(userObject);
		broker.setUser(userObject);
		brokerRepository.save(broker);
		
		return new ResponseEntity<>("Da them broker thanh cong", HttpStatus.OK);
		//return null;
		
	}
	
	/**
	 * @Summary Admin manage edit broker
	 * @date 
	 * @author Tu Viet Van
	 * @throws ParseException 
	 * @Param 
	 * @Return 
	 **/
//	@PutMapping(path = "/editBroker1")
//	public ResponseEntity<Object> editUser(@Valid @RequestBody BrokerDTOEdit brokerDTOEdit, BindingResult result) throws ParseException {
//		
//		System.out.println("xin chao edit");
////		SimpleDateFormat format = new SimpleDateFormat(
////				"yyy-MM-dd HH:mm:ss");
//		
//		SimpleDateFormat format = new SimpleDateFormat(
//				"yyyy-MM-dd");
//		String email = brokerDTOEdit.getEmail();
//		User user = userService.findByEmail(email);
//		if(user == null) {
//			return new ResponseEntity<>("Khong ton tai user de sua", HttpStatus.NOT_FOUND);
//		}
//		String nameBroker = brokerDTOEdit.getNameBroker();
//		Boolean gender = brokerDTOEdit.getGender();
//		String phoneNumber = brokerDTOEdit.getPhoneNumber();
//		String address = brokerDTOEdit.getAddress();
//		Boolean active = brokerDTOEdit.getActive();
//		Boolean nonDel = brokerDTOEdit.getNonDel();
//		String password = brokerDTOEdit.getPassword();
//		String dateOfBirth = brokerDTOEdit.getDateOfBirth();
//		Date date = format.parse(dateOfBirth);
//		//String role = brokerDTOEdit.getRole();
//		//Broker broker = new Broker();
//		//cai nay phan quyen sau...
//		//User userObject = new User();
//		//userObject.setEmail(email);
//		//user.setPassword(password);
//		user.setActive(active);
//		user.setNameUser(nameBroker);
//		user.setGender(gender);
//		user.setPhoneNumber(phoneNumber);
//		user.setAddress(address);
//		user.setDayOfBirth(date);
//		user.setNonDel(nonDel);
////		Optional<Role> roleObject = roleRepository.findByRoleName(role);
////		if(roleObject.get() == null) {
////			return new ResponseEntity<>("Banj cap quyen sai cho Role", HttpStatus.NOT_FOUND);
////		}
//		//Role roleO = roleObject.get();
//		//roleRepository.save(roleO);
//		//user.setRole(roleO);
//		userRepository.save(user);
//		//broker.setUser(user);
//		//brokerRepository.save(broker);	
//		return new ResponseEntity<>("Da sua broker thanh cong", HttpStatus.OK);	
//	}
//	@DeleteMapping(path = "/broker/{id}")
//	public ResponseEntity<Object> removeUserById(@PathVariable("id") long id) {
	
	@PutMapping(path = "/editBroker1/{id}")
	public ResponseEntity<Object> editUser(@PathVariable("id") Long id, @Valid @RequestBody BrokerDTOEdit brokerDTOEdit, BindingResult result) throws ParseException {
		
		System.out.println("xin chao edit");
//		SimpleDateFormat format = new SimpleDateFormat(
//				"yyy-MM-dd HH:mm:ss");
		
		Optional<Broker> bro = brokerRepository.findById(id);
		if(!bro.isPresent()) {
			return new ResponseEntity<>("Sai id cua Broker roi.", HttpStatus.NOT_FOUND);
		}
		Broker broker = bro.get();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String email = brokerDTOEdit.getEmail();
		//User user = userService.findByEmail(email);
		User user = broker.getUser();
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
		//String role = brokerDTOEdit.getRole();
		//Broker broker = new Broker();
		//cai nay phan quyen sau...
		//User userObject = new User();
		//userObject.setEmail(email);
		//user.setPassword(password);
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
	
	
	/**
	 * @Summary Admin manage delete broker simple
	 * @date 
	 * @author Tu Viet Van
	 * @throws ParseException 
	 * @Param id
	 * @Return void
	 **/
	
	@DeleteMapping(path = "/broker/{id}")
	public ResponseEntity<Object> removeUserById(@PathVariable("id") long id) {
		Broker broker = brokerRepository.getOne(id);
		if(broker == null) {
			return new ResponseEntity<>("Not found broker suite with its id", HttpStatus.NOT_FOUND);
		}
		User user = broker.getUser();
		user.setNonDel(false);
		userRepository.save(user);
		
		return new ResponseEntity<Object>("Da xoa thanh cong...", HttpStatus.OK);
	}
	
	/**
	 * @Summary Search list broker by name or address or phone number
	 * @date
	 * @author Tu Viet Van
	 * @throws ParseException 
	 * @Param text
	 * @Return List<Broker>
	 */
	@GetMapping(path = "/filterBroker")
	public ResponseEntity<Object> FilterBroker(@RequestParam("search") String search) throws ParseException {
		if (search.isEmpty()) {
			
			List<Broker> listBroker = brokerService.findAll();
			List<BrokerResponse> listBroRes = new ArrayList<>();
			BrokerResponse broRes = null;
			for(Broker bro: listBroker) {
				broRes = brokerService.convertToResponse(bro);
				listBroRes.add(broRes);
			}
			
			return new ResponseEntity<>(listBroRes, HttpStatus.OK);
		
		}
		//findByEmployeeNameContainingAnd
		
		//List<UserResponse> emp = employeeService.listEmployeeByFilter(key);
		//return new ResponseEntity<>(emp, HttpStatus.OK);
		List<BrokerResponse> listBrokerResponse = brokerService.listBrokerByFilter(search);
		return new ResponseEntity<>(listBrokerResponse, HttpStatus.OK);
	}
	
//	@GetMapping(path = "/filterCustomer")
//	public ResponseEntity<Object> FilterCustomer(@RequestParam("search") String search) throws ParseException {
//		if (search.isEmpty()) {
//			
//			List<Broker> listBroker = brokerService.findAll();
//			List<BrokerResponse> listBroRes = new ArrayList<>();
//			BrokerResponse broRes = null;
//			for(Broker bro: listBroker) {
//				broRes = brokerService.convertToResponse(bro);
//				listBroRes.add(broRes);
//			}
//			
//			return new ResponseEntity<>(listBroRes, HttpStatus.OK);
//		
//		}
//		//findByEmployeeNameContainingAnd
//		
//		//List<UserResponse> emp = employeeService.listEmployeeByFilter(key);
//		//return new ResponseEntity<>(emp, HttpStatus.OK);
//		List<BrokerResponse> listBrokerResponse = brokerService.listBrokerByFilter(search);
//		return new ResponseEntity<>(listBrokerResponse, HttpStatus.OK);
//	}
	
	
	
	/**
	 * @summary Admin manage list of all Customer in system
	 * @date sep 12, 2019
	 * @author Tu Viet Van
	 * @param  none
	 * @return listCustomer
	 **/
	
	@GetMapping(path = "/listCustomer/{pageIndex}")
	public ResponseEntity<Object> displayListCustomer(@PathVariable("pageIndex") int pageIndex, Pageable page) {
		pageIndex = pageIndex - 1;
		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 2, Sort.by("id"));
		List<Customer> listCustomer = customerService.findAll(firstPageWithTwoElements);
//		if(listCustomer == null) {
//			return new ResponseEntity<>("List customer is empty.", HttpStatus.NOT_FOUND);
//		}
////		List<CustomerResponse> listCusRes = new ArrayList<>();
//		CustomerResponse cusRes = null;
//		for(Customer customer: listCustomer) {
//			cusRes = customerService.convertToCusRes(customer);
//			listCusRes.add(cusRes);
//		}
//		if(listCusRes == null) {
//			return new ResponseEntity<>("Not Found list customer in system", HttpStatus.NOT_FOUND);
//		}
//		
		return new ResponseEntity<>(listCustomer, HttpStatus.OK);
		
		//return new ResponseEntity<>(listCusRes, HttpStatus.OK);
	}
	
	/**
	 * @summary Admin manage detail customer in system
	 * @date 
	 * @author Tu Viet Van
	 * @param  id
	 * @return Customer
	 **/
	
	@GetMapping(path = "customer/{id}")
	public ResponseEntity<Object> detailCustomer(@PathVariable("id") long id) {
	    Customer customer = customerService.getCustomerById(id);
	    if(customer == null) {
	    	return new ResponseEntity<Object>("Not Found Customer by specific id", HttpStatus.NOT_FOUND);
	    }
	    CustomerResponse cusRes = customerService.convertToCusRes(customer);
	    
	    return new ResponseEntity<>(cusRes, HttpStatus.OK);
	}
	
	
	
	@GetMapping(path = "customers/{id}")
	public ResponseEntity<Object> detailCustomers(@PathVariable("id") long id) throws ParseException {
		Optional<CustomerNotAuthority> cus = customerNotAuthorityRepository.findById(id);
		if(!cus.isPresent()) {
			return new ResponseEntity<>("Not found this id", HttpStatus.NOT_FOUND);
		}
//	    CustomerNotAuthority customer = customerNotAuthorityService.getCustomerById(id);
//	    if(customer == null) {
//	    	return new ResponseEntity<Object>("Not Found Customer by specific id", HttpStatus.NOT_FOUND);
//	    }
	    CustomerNotAuthorityResponse cusRes = customerNotAuthorityService.convertToCusRes(cus.get());
	    
	    return new ResponseEntity<>(cusRes, HttpStatus.OK);
	}
	
	
	
	
	
	
	/**
	 * @summary Admin manage add customer in system
	 * @date 
	 * @author Tu Viet Van
	 * @param  id
	 * @return 
	 **/
	@PostMapping(path = "/addCustomer")
	public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult result) throws ParseException {
		
		System.out.println("Hello Add Customer");
		
		SimpleDateFormat format = new SimpleDateFormat(
				"yyy-MM-dd HH:mm:ss");
		String email = customerDTO.getEmail();
		User user = userService.findByEmail(email);
		if(user != null) {
			return new ResponseEntity<>("User nay da ton tai", HttpStatus.NOT_FOUND);
		}
		String nameCustomer = customerDTO.getNameCustomer();
		Boolean gender = customerDTO.getGender();
		String phoneNumber = customerDTO.getPhoneNumber();
		String address = customerDTO.getAddress();
		Boolean active = customerDTO.getActive();
		String password = customerDTO.getPassword();
		String dateOfBirth = customerDTO.getDateOfBirth();
		Date date = format.parse(dateOfBirth);
		//String role = customerDTO.getRole();
		String role = customerDTO.getRole();
		if(!role.equalsIgnoreCase(RoleConst.CUSTOMER)) {
			return new ResponseEntity<>("Set Role Error", HttpStatus.NOT_FOUND);
		}
		Customer customer = new Customer();
		
		//cai nay phan quyen sau...
		User userObject = new User();
		userObject.setEmail(email);
		userObject.setPassword(password);
		userObject.setActive(active);
		userObject.setNameUser(nameCustomer);
		userObject.setGender(gender);
		userObject.setPhoneNumber(phoneNumber);
		userObject.setAddress(address);
		userObject.setNonDel(true);
		userObject.setNonLocked(true);
		//userObject.setDayOfBirth(date);
		Optional<Role> roleObject = roleRepository.findByRoleName(role);
		if(!roleObject.isPresent()) {
			return new ResponseEntity<>("Ban can cap quyen cho Customer", HttpStatus.NOT_FOUND);
		}
		Role roleO = roleObject.get();
		roleRepository.save(roleO);
		userObject.setRole(roleO);
		userRepository.save(userObject);
		customer.setUser(userObject);
		customerRepository.save(customer);
		
		return new ResponseEntity<>("Da them customer thanh cong", HttpStatus.OK);
		
	}
	
	/**
	 * @summary Admin manage edit customer in sytem
	 * @date 
	 * @author Tu Viet Van
	 * @param  
	 * @return 
	 **/
	
	@PutMapping(path = "/editCustomer")
	public ResponseEntity<Object> editCustomer(@Valid @RequestBody CustomerDTOEdit customerDTOEdit, BindingResult result) throws ParseException {
		
		System.out.println("Hello edit customer in system...");
		SimpleDateFormat format = new SimpleDateFormat(
				"yyy-MM-dd HH:mm:ss");
		String email = customerDTOEdit.getEmail();
		User user = userService.findByEmail(email);
		if(user == null) {
			return new ResponseEntity<>("Khong ton tai user de sua", HttpStatus.NOT_FOUND);
		}
		String nameCustomer = customerDTOEdit.getNameCustomer();
		Boolean gender = customerDTOEdit.getGender();
		String phoneNumber = customerDTOEdit.getPhoneNumber();
		String address = customerDTOEdit.getAddress();
		Boolean active = customerDTOEdit.getActive();
		Boolean nonDel = customerDTOEdit.getNonDel();
		String password = customerDTOEdit.getPassword();
		String dateOfBirth = customerDTOEdit.getDateOfBirth();
		Date date = format.parse(dateOfBirth);
		String role = customerDTOEdit.getRole();
		Customer broker = new Customer();
		//cai nay phan quyen sau...
		//User userObject = new User();
		//userObject.setEmail(email);
		//user.setPassword(password);
		user.setActive(active);
		user.setNameUser(nameCustomer);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setAddress(address);
		//user.setDayOfBirth(date);
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
		return new ResponseEntity<>("Da sua customer thanh cong", HttpStatus.OK);	
	}
	
	/**
	 * @summary Admin manage delete customer in system
	 * @date 
	 * @author Tu Viet Van
	 * @param  
	 * @return 
	 **/
	@DeleteMapping(path = "/customer/{id}")
	public ResponseEntity<Object> removeCustomerById(@PathVariable("id") long id) {
		Customer customer = customerRepository.getOne(id);
		if(customer == null) {
			return new ResponseEntity<>("Not found customer suite with its id", HttpStatus.NOT_FOUND);
		}
		User user = customer.getUser();
		user.setNonDel(false);
		userRepository.save(user);
		
		return new ResponseEntity<Object>("Da xoa thanh cong khach hang co trong he thong...", HttpStatus.OK);
	}
	
	/**
	 * @summary Admin manage search info customer have in system
	 * @date 
	 * @author Tu Viet Van
	 * @param  
	 * @return 
	 **/
	@GetMapping(path = "/filterCustomer")
	public ResponseEntity<Object> FilterCustomer(@RequestParam("search") String search) {
		if (search.isEmpty()) {
			
			List<Customer> listCustomer = customerService.findAll();
			
			
			if(listCustomer != null) {
				return new ResponseEntity<>(listCustomer, HttpStatus.NOT_FOUND);
			}
			List<CustomerResponse> listCusRes = new ArrayList<>();
			CustomerResponse cusRes = null;
			for(Customer bro: listCustomer) {
				cusRes = customerService.convertToCusRes(bro);
				listCusRes.add(cusRes);
			}
			
			return new ResponseEntity<>(listCusRes, HttpStatus.OK);
		
		}
		//findByEmployeeNameContainingAnd
		
		//List<UserResponse> emp = employeeService.listEmployeeByFilter(key);
		//return new ResponseEntity<>(emp, HttpStatus.OK);
		//List<CustomerResponse> deleteCustomer = customerService.listCustomerByFilter(search);
		List<Customer> deleteCustomer1 = customerService.listCustomerByFilter1(search);
		if(deleteCustomer1 == null) {
			return new ResponseEntity<>("khong ti thay gi", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deleteCustomer1, HttpStatus.OK);
	}
	
	//filter owner in system
	@GetMapping(path = "/filterOwner")
	public ResponseEntity<Object> filterOwner(@RequestParam("search") String search) {
		if (search.isEmpty()) {
			
			List<Owner> listOwner = ownerRepository.findAll();
			
			
			if(listOwner != null) {
				return new ResponseEntity<>(listOwner, HttpStatus.NOT_FOUND);
			}
//			List<CustomerResponse> listCusRes = new ArrayList<>();
//			CustomerResponse cusRes = null;
//			for(Customer bro: listCustomer) {
//				cusRes = customerService.convertToCusRes(bro);
//				listCusRes.add(cusRes);
//			}
//			
//			return new ResponseEntity<>(listCusRes, HttpStatus.OK);
		
		}
		//findByEmployeeNameContainingAnd
		
		//List<UserResponse> emp = employeeService.listEmployeeByFilter(key);
		//return new ResponseEntity<>(emp, HttpStatus.OK);
		//List<CustomerResponse> deleteCustomer = customerService.listCustomerByFilter(search);
//		List<Customer> deleteCustomer1 = customerService.listCustomerByFilter1(search);
//		if(deleteCustomer1 == null) {
//			return new ResponseEntity<>("khong ti thay gi", HttpStatus.NOT_FOUND);
//		}
		List<Owner> listOwner = ownerRepository.findByIdCardContainingOrOwnerNameContainingOrPhoneNumber(search, search, search);
		if((listOwner == null) || (listOwner.size() == 0)) {
			return new ResponseEntity<>("Khong tim thay bat ky owner nao!!!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listOwner, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "detailOwner/{id}")
	public ResponseEntity<Object> detailOwner(@PathVariable("id") long id) {
		Optional<Owner> ownerOptional = ownerRepository.findById(id);
		if(!ownerOptional.isPresent()) {
			return new ResponseEntity<>("Not found any owner in system!!!", HttpStatus.NOT_FOUND);
		}
		Owner owner = ownerOptional.get();
		return new ResponseEntity<>(owner, HttpStatus.OK);
	}
	
	@GetMapping(path = "/filterCustomerNotAuthority")
	public ResponseEntity<Object> FilterCustomerNotAuthority(@RequestParam("search") String search) {
		if (search.isEmpty()) {
			
			List<CustomerNotAuthority> listCustomerNotAuthority = customerNotAuthorityRepository.findAll();
			
			
//			if(listCustomerNotAuthority != null) {
//				return new ResponseEntity<>(listCustomerNotAuthority, HttpStatus.NOT_FOUND);
//			}
//			List<CustomerResponse> listCusRes = new ArrayList<>();
//			CustomerResponse cusRes = null;
//			for(Customer bro: listCustomer) {
//				cusRes = customerService.convertToCusRes(bro);
//				listCusRes.add(cusRes);
//			}
			return new ResponseEntity<>(listCustomerNotAuthority, HttpStatus.OK);
			
//			List<CustomerResponse> listCusRes = new ArrayList<>();
//			
//			
//			return new ResponseEntity<>(listCusRes, HttpStatus.OK);
		
		}
		//findByEmployeeNameContainingAnd
		
		//List<UserResponse> emp = employeeService.listEmployeeByFilter(key);
		//return new ResponseEntity<>(emp, HttpStatus.OK);
		//List<CustomerResponse> deleteCustomer = customerService.listCustomerByFilter(search);
		List<CustomerNotAuthority> listCustomerNotAuthority = customerNotAuthorityRepository.findByIdCardContaining(search);
		if((listCustomerNotAuthority == null) || (listCustomerNotAuthority.size() == 0)) {
			return new ResponseEntity<>("Không tồn tại khách hàng này!!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listCustomerNotAuthority, HttpStatus.OK);
	}
	
//code thu nghiem server tai day
	@GetMapping(path = "/listRole")
	public ResponseEntity<Object> a() {
		List<Role> listRole = roleRepository.findAll();
		
		return new ResponseEntity<>(listRole, HttpStatus.OK);
	}
	
	/**
	 * @summary Admin manage list all post that its state is Pending in the system.
	 * @date 
	 * @author Tu Viet Van
	 * @param  
	 * @return 
	 **/
//	@GetMapping(path = "/listCustomer/{pageIndex}")
//	public ResponseEntity<Object> displayListCustomer(@PathVariable("pageIndex") int pageIndex, Pageable page) {
//		pageIndex = pageIndex - 1;
//		Pageable firstPageWithTwoElements = PageRequest.of(pageIndex, 2, Sort.by("id"));
//		List<Customer> listCustomer = customerService.findAll(firstPageWithTwoElements);
//	
	
	
	@GetMapping(path = "/listPostPending/{pageIndex}")
	public ResponseEntity<Object> listPostPending(@PathVariable("pageIndex") int pageIndex, Pageable page) {
		
		pageIndex = pageIndex - 1;
		Pageable phantrang = PageRequest.of(pageIndex, 15, Sort.by("id"));
		List<Product> listProduct = productRepository.findByRequestStatusAndNonDel(RequestState.PENDING, true, phantrang);
		if(listProduct == null) {
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
	
	//lay tong so trang 
	@GetMapping(path = "/getNumberOfPagePending")
	public ResponseEntity<Object> getAllPagesPending() {
		
		List<Product> listProduct = productRepository.findByRequestStatusAndNonDel(RequestState.PENDING, true);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
		}
		if(listProduct.size() % 5 == 0) {
			return new ResponseEntity<>(listProduct.size()/5, HttpStatus.OK);
		}
		
		return new ResponseEntity<>((listProduct.size()/5 + 1), HttpStatus.OK);
	}
	
	@GetMapping(path = "/getNumberOfPageApproved")
    public ResponseEntity<Object> getAllPagesApproved() {
		
		List<Product> listProduct = productRepository.findByRequestStatusAndNonDel(RequestState.APPROVED, true);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
		}
		if(listProduct.size() % 5 == 0) {
			return new ResponseEntity<>(listProduct.size() / 5, HttpStatus.OK);
		}
		return new ResponseEntity<>((listProduct.size() / 5 + 1), HttpStatus.OK);
	}
	 
	@GetMapping(path = "/getNumberOfPages")
     public ResponseEntity<Object> getAllPages() {
		
		List<Product> listProduct = productRepository.findByNonDel(true);
		if((listProduct == null) || (listProduct.size() == 0)) {
			return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
		}
		if(listProduct.size() % 5 == 0) {
			return new ResponseEntity<>(listProduct.size() / 5, HttpStatus.OK);
		}
		return new ResponseEntity<>((listProduct.size() / 5 + 1), HttpStatus.OK);
	}
	
	
	/**
	 * @summary Admin manage list all post that its state is Approved in the system.
	 * @date 
	 * @author Tu Viet Van
	 * @param  
	 * @return 
	 **/
	@GetMapping(path = "/listPostApproved/{pageIndex}")
	public ResponseEntity<Object> listPostApproved(@PathVariable("pageIndex") int pageIndex, Pageable page) {
		
		pageIndex = pageIndex - 1;
		Pageable phantrang = PageRequest.of(pageIndex, 15, Sort.by("id"));
		List<Product> listProduct = productRepository.findByRequestStatusAndNonDel(RequestState.APPROVED, true,  phantrang);
		if(listProduct == null) {
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
	
	//all post
	@GetMapping(path = "/listAllPosts/{pageIndex}")
	public ResponseEntity<Object> listAllPosts(@PathVariable("pageIndex") int pageIndex, Pageable page) {
		
		pageIndex = pageIndex - 1;
		Pageable phantrang = PageRequest.of(pageIndex, 15, Sort.by("id"));
		List<Product> listProduct = productRepository.findByNonDel(true, phantrang);
		if(listProduct == null) {
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
	
	
	
	
	
	
	@GetMapping(path = "/listPostApprovedAndNotSell/{pageIndex}")
	public ResponseEntity<Object> listPostApprovedAndNoSell(@PathVariable("pageIndex") int pageIndex, Pageable page) {
		
		pageIndex = pageIndex - 1;
		Pageable phantrang = PageRequest.of(pageIndex, 2, Sort.by("id"));
//		List<Product> listProduct = productService.findByRequestStatus(RequestState.APPROVED, phantrang);
		List<Product> listProduct = productService.findByRequestStatusAndProductStatus(RequestState.APPROVED, false, phantrang);
		
		
		if(listProduct == null) {
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
	
	
	
	
	/**
	 * @Summary Admin manage detail of post in system.
	 * @date  
	 * @author Tu Viet Van
	 * @Param id
	 * @Return 
	 **/
	@GetMapping(path = "/post/{id}")
	public ResponseEntity<Object> detailPost(@PathVariable("id") long id) {
		Product product = productService.findById(id);
		if(product == null) {
			return new ResponseEntity<>("NOT FOUND ANY POST IN SYSTEM", HttpStatus.NOT_FOUND);
		}
		ProductPublicResponse p = productService.convertToProductResponsePublic(product);

        return new ResponseEntity<>(p, HttpStatus.OK);
		
	}
	
	@DeleteMapping(path = "/post/{id}")
	public ResponseEntity<Object> delPost(@PathVariable("id") long id) {
		Product product = productService.findById(id);
		if(product == null) {
			return new ResponseEntity<>("NOT FOUND ANY POST IN SYSTEM", HttpStatus.NOT_FOUND);
		}
		product.setNonDel(false);
		productRepository.save(product);

        return new ResponseEntity<>("Ban da xoa thanh cong!!!", HttpStatus.OK);
		
	}
	
	/**
	 * @Summary Search list broker by name or address or phone number
	 * @date
	 * @author Tu Viet Van
	 * @Param text
	 * @Return List<Broker>
	 */
	@GetMapping(path = "/filterPost")
	public ResponseEntity<Object> FilterPost(@RequestParam("search") String search) {
		if (search.isEmpty()) {
			
			List<Product> listProduct = productService.findAll();
			if(listProduct == null) {
				return new ResponseEntity<>("NOT FOUND ANY RECORD IN SYSTEM", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(listProduct, HttpStatus.OK);
		
		}
		//findByEmployeeNameContainingAnd
		
		//List<UserResponse> emp = employeeService.listEmployeeByFilter(key);
		//return new ResponseEntity<>(emp, HttpStatus.OK);
		List<Product> listProduct = productService.listProductByFilter(search);
		return new ResponseEntity<>(listProduct, HttpStatus.OK);
	}
	
	/**
	 * @Summary Admin manage approve post in system
	 * @Author: Tu Viet Van
	 * 
	 */
	@GetMapping(path = "/handlePost/{id}")
	public ResponseEntity<Object> handlePost(@PathVariable("id") long id, @RequestParam("status") String status) {
		Product product = productService.findById(id);
		if(product == null) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
//		
		//se co 1 danh sach cac request status hien tren menu
		if(status.equals("APPROVED")) {
			
		    product.setRequestStatus(RequestState.APPROVED);
		
		} else if(status.equals("PENDING")) {
		    product.setRequestStatus(RequestState.PENDING);
		} else {
			product.setRequestStatus("DELETE");
		}
		productRepository.save(product);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
//	}
	
//	@GetMapping(path = "/handlePost/{id}")
//	public ResponseEntity<Object> handlePost1(@PathVariable("id") long id) {
//		Product product = productService.findById(id);
//		if(product == null) {
//			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
//		}
//		
//		//se co 1 danh sach cac request status hien tren menu
////		product.setRequestStatus(RequestState.APPROVED);
////		productRepository.save(product);
//		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	//Admin approve these post one way simple
	@PutMapping(path = "/handlePostToApproved/{id}")
	public ResponseEntity<Object> handlePostApproved(@PathVariable("id") long id) {
		Product product = productService.findById(id);
		if(product == null) {
			return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
		}
		product.setRequestStatus("APPROVED");
		productRepository.save(product);
		return new ResponseEntity<>("Ban da phe duyet bai dang thanh cong!!!", HttpStatus.OK);
	}
	
	@GetMapping(path = "/handlePostToApproved/{id}")
	public ResponseEntity<Object> handlePostApprovedByGetMapping(@PathVariable("id") long id) {
		Product product = productService.findById(id);
		if(product == null) {
			return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
		}
		product.setRequestStatus("APPROVED");
		
		productRepository.save(product);
		ProductPublicResponse pro = productService.convertToProductResponsePublic(product);
		return new ResponseEntity<>(pro, HttpStatus.OK);
	}
	
	@PutMapping(path = "/handlePost")
	public ResponseEntity<Object> handlePost(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
		Long id = productDTO.getId();
		Product product = productService.findById(id);
		if(product == null) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		product.setRequestStatus(productDTO.getRequestStatus());
		//product.setProductCost(product.getProductCost());
		
		//product1.setRequestStatus("abcd");
		//productRepository.save(product1);		
		productRepository.save(product);
		
		return new ResponseEntity<>("hkhhkhkhkhkhkhkhkhkkhkh", HttpStatus.OK);
	}
	
	//test thu tai day
	@PostMapping(path = "/testThu")
	public ResponseEntity<Object> testThu(@Valid @RequestBody TestThuDTO testThuDTO, BindingResult result) throws ParseException {
		
		
		return new ResponseEntity<>("Test thu nghiem testThuDTO" + testThuDTO.getA(), HttpStatus.OK);
	
	}
	
	/**
	 * Admin assign owner with broker to inspect position
	 * 1 Broker co nhieu owner
	 * @throws JsonProcessingException 
	 */
	@PostMapping(path = "/assignBrokerWithOwner")
	public ResponseEntity<Object> assignBroker(@Valid @RequestBody AssignBrokerWithCustomerDTO  awcd, BindingResult result,  Principal p) throws JsonProcessingException {
		
		AssignBrokerWithCustomerDTOResponse assignBrokerWithCustomerDTOResponse = new AssignBrokerWithCustomerDTOResponse();
		if(result.hasErrors()){          
	    	  System.out.println(result.getAllErrors().toString());
	          Map<String, String> errors = result.getFieldErrors().stream()
	                .collect(
	                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
	                  );	        
//	          if(result.getAllErrors().toString().indexOf("PasswordMatches")!= -1) {
//	        	  errors.put("matchingPassword", "Password is not matched");
//	          }c
	          assignBrokerWithCustomerDTOResponse.setValidated(false);
	          assignBrokerWithCustomerDTOResponse.setErrorMessages(errors); 
//	          ObjectMapper mapper = new ObjectMapper();
//	          String json = mapper.writeValueAsString(assignBrokerWithCustomerDTOResponse);
//	          return new ResponseEntity<Object>(json, HttpStatus.OK);
	          //return new ResponseEntity<>(assignBrokerWithCustomerDTOResponse, HttpStatus.OK);
	          
	          Map<String, String> map = assignBrokerWithCustomerDTOResponse.getErrorMessages();
	          
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
		
		String email = p.getName();
		Optional<User> u = userRepository.findByEmail(email);
		if(!u.isPresent()) {
			return new ResponseEntity<>("Sai quyen admin roi", HttpStatus.OK);
		}
		User user = u.get();
		Admin admin = user.getAdmin();
		String ownerName = awcd.getOwnerName();
		long broker_id = awcd.getBrokerId();
		Optional<Broker> bro = brokerRepository.findById(broker_id);
		if(!bro.isPresent()) {
			return new ResponseEntity<>("Not Found This Broker", HttpStatus.OK);
		}
		Broker broObject = bro.get();
		Owner owner = new Owner();
		owner.setOwnerName(ownerName);
		owner.setIdCard(awcd.getIdCard());
		owner.setPhoneNumber(awcd.getPhoneNumber());
		owner.setAddress(awcd.getAddress());
		owner.setBroker(broObject);
		owner.setAdmin(admin);
		
		
		ownerRepository.save(owner);
		return new ResponseEntity<>("Da chi dinh thanh cong Broker voi chu san pham nao...", HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/detail-change-password")
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
				//messages.add(objectError.getDefaultMessage());
				return new ResponseEntity<>(objectError.getDefaultMessage(), HttpStatus.OK);
				
			}
			//return new ResponseEntity<>(messages, HttpStatus.OK);
//			modelMap.addAttribute("changePasswordDTO", passwordDTO);
//			return "change-password";			
		}
	    if(!dbPassword.equals(passwordDTO.getPasswordCurrent())) {
	    	return new ResponseEntity<>("Bạn nhập sai password hiện tại...", HttpStatus.OK);
	    }
	    
	    boolean checkDuplicateMatchingPassword = passwordService.checkDuplicateMatchingPassword(newPassword, newMatchingPassword);
	    if(!checkDuplicateMatchingPassword) {
			return new ResponseEntity<>("Xác nhận password mới không trùng với password mới", HttpStatus.NOT_FOUND);
		}
		
	    user.setPassword(newPassword);
		userRepository.save(user);
	    
	 return new ResponseEntity<>(user, HttpStatus.OK);
	
}
	
	
	@GetMapping(path = "/detail")
	public ResponseEntity<Object> detailAdmin(Principal p) {
		String date;
		String email = p.getName();
		Optional<User> u = userRepository.findByEmail(email);
		if(!u.isPresent()) {
			return new ResponseEntity<>("NOT FOUND USER HAVE THIS EMAIL", HttpStatus.NOT_FOUND);
		}
		User user = u.get();
		Admin admin = user.getAdmin();
		AdminResponse adminResponse = new AdminResponse();
		adminResponse.setId(user.getAdmin().getId());
		adminResponse.setEmail(user.getEmail());
		adminResponse.setNameAdmin(user.getNameUser());
		adminResponse.setActive(user.getActive());
		adminResponse.setNonDel(user.getNonDel());
		adminResponse.setGender(user.getGender());
		adminResponse.setPhoneNumber(user.getPhoneNumber());
		adminResponse.setAddress(user.getAddress());
		DateFormat format1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		date = format1.format(user.getDayOfBirth());
		adminResponse.setDateOfBirth(date);
		adminResponse.setLinkImageProfile("https://tiger010203.herokuapp.com/downloadFileProfile" + "/" + user.getdBFileProfile().getId());
		
		return new ResponseEntity<>(adminResponse, HttpStatus.OK);		
	}
	
	@GetMapping(path = "/logout")
	public ResponseEntity<Object> logout(Principal p) {
		
		return null;
	}
	
	
	@PostMapping(path = "/addPostByAdminNewCustomer")
	public ResponseEntity<Object> addPostByAdmin(@Valid @RequestBody FinalProductAdmin finalProductAdmin, BindingResult result, Principal principal) throws ParseException {
		
//		String email = p.getName();
//		Optional<User> user = userRepository.findByEmail(email);

		String email = principal.getName();
		Admin admin = adminRepository.findByUserEmail(email);
		if(admin == null) {
			return new ResponseEntity<>("Sai Admin roi!!!", HttpStatus.NOT_FOUND);
		}
		
				
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
		long idBroker = finalProductAdmin.getBrokerId();
		Optional<Broker> bro = brokerRepository.findById(idBroker);
	    if(!bro.isPresent()) {
	    	return new ResponseEntity<>("Not found id of broker.", HttpStatus.NOT_FOUND);
	    }
	    Broker broker = bro.get();
	    
	    //product nay lay tu danh sach cac bai dang ma chua chi dinh broker va customer not authority!!!
	    //se co 1 api rieng cua no!!!
		long idProduct = finalProductAdmin.getIdProduct();
		Optional<Product> p = productRepository.findById(idProduct);
		if(!p.isPresent()) {
			return new ResponseEntity<>("Loi Id của product rồi", HttpStatus.NOT_FOUND);
		}
		Product product = p.get();
		product.setBroker(broker);
		product.setAdmin(admin);
		
		
//	    Optional<CustomerNotAuthority> cusnotAuth = customerNotAuthorityRepository.findById(finalProductAdmin.getCustomerId());
//	    
//	    if(!cusnotAuth.isPresent()) {
//		String nameCustomer = finalProductAdmin.getNameCustomer();
//		String phoneNumber = finalProductAdmin.getPhoneNumber();
//		String dayOfBirth = finalProductAdmin.getDayOfBirth();
//		String description = finalProductAdmin.getDescription();
//		Date date = format.parse(dayOfBirth);
//		CustomerNotAuthority customerNotAuthority = new CustomerNotAuthority();
//		customerNotAuthority.setNameCustomer(nameCustomer);
//		customerNotAuthority.setPhoneNumber(phoneNumber);
//		customerNotAuthority.setDayOfBirth(date);
//		customerNotAuthority.setDescription(description);
//		
//		customerNotAuthorityRepository.save(customerNotAuthority);
//		product.setCustomerNotAuthority(customerNotAuthority);
//	    } else {
//	    	customerNotAuthorityRepository.save(cusnotAuth.get());
//	    	product.setCustomerNotAuthority(cusnotAuth.get());
//	    }
//	    product.setProductStatus(true);
//		productRepository.save(product);		
//		return new ResponseEntity<>(product, HttpStatus.OK);
		String idCard = finalProductAdmin.getIdCard();
		String nameCustomer = finalProductAdmin.getNameCustomer();
		String phoneNumber = finalProductAdmin.getPhoneNumber();
		String dayOfBirth = finalProductAdmin.getDayOfBirth();
		Date date = format.parse(dayOfBirth);
		CustomerNotAuthority customerNotAuthority = new CustomerNotAuthority();
		customerNotAuthority.setIdCard(idCard);
		customerNotAuthority.setNameCustomer(nameCustomer);
		customerNotAuthority.setPhoneNumber(phoneNumber);
		customerNotAuthority.setDayOfBirth(date);
		customerNotAuthorityRepository.save(customerNotAuthority);
		product.setCustomerNotAuthority(customerNotAuthority);
		product.setProductStatus(true);
		productRepository.save(product);
		
		return new ResponseEntity<>("Bạn đã đăng bài thành công !!!", HttpStatus.OK);
	}
	
	@PostMapping(path = "/addPostByAdminOldCustomer")
	public ResponseEntity<Object> addPostByAdminOldCustomer(@Valid @RequestBody FinalProductAdmin finalProductAdmin, BindingResult result, Principal principal) throws ParseException {
		
		String email = principal.getName();
		Admin admin = adminRepository.findByUserEmail(email);
		if(admin == null) {
			return new ResponseEntity<>("Sai Admin roi!!!", HttpStatus.OK);
		}
		
		SimpleDateFormat format = new SimpleDateFormat(
				"yyy-MM-dd HH:mm:ss");
		long idBroker = finalProductAdmin.getBrokerId();
		Optional<Broker> bro = brokerRepository.findById(idBroker);
	    if(!bro.isPresent()) {
	    	return new ResponseEntity<>("Not found id of broker.", HttpStatus.OK);
	    }
	    Broker broker = bro.get();
	    
	    //product nay lay tu danh sach cac bai dang ma chua chi dinh broker va customer not authority!!!
	    //se co 1 api rieng cua no!!!
		long idProduct = finalProductAdmin.getIdProduct();
		Optional<Product> p = productRepository.findById(idProduct);
		if(!p.isPresent()) {
			return new ResponseEntity<>("Loi Id của product rồi", HttpStatus.OK);
		}
		Product product = p.get();
		product.setBroker(broker);
		
		product.setAdmin(admin);
		
		
	    Optional<CustomerNotAuthority> cusnotAuth = customerNotAuthorityRepository.findById(finalProductAdmin.getCustomerId());	    
	    
	    if(!cusnotAuth.isPresent()) {
	    	return new ResponseEntity<>("Loi khach hang roi!!!", HttpStatus.OK);
	    }
	    CustomerNotAuthority customerNotAuthority = cusnotAuth.get();
	    product.setCustomerNotAuthority(customerNotAuthority);
	    product.setProductStatus(true);
	    productRepository.save(product);
	    
	    
	    
	    
	    
//	    if(!cusnotAuth.isPresent()) {
//		String nameCustomer = finalProductAdmin.getNameCustomer();
//		String phoneNumber = finalProductAdmin.getPhoneNumber();
//		String dayOfBirth = finalProductAdmin.getDayOfBirth();
//		String description = finalProductAdmin.getDescription();
//		Date date = format.parse(dayOfBirth);
//		CustomerNotAuthority customerNotAuthority = new CustomerNotAuthority();
//		customerNotAuthority.setNameCustomer(nameCustomer);
//		customerNotAuthority.setPhoneNumber(phoneNumber);
//		customerNotAuthority.setDayOfBirth(date);
//		customerNotAuthority.setDescription(description);
//		
//		customerNotAuthorityRepository.save(customerNotAuthority);
//		product.setCustomerNotAuthority(customerNotAuthority);
//	    } else {
//	    	customerNotAuthorityRepository.save(cusnotAuth.get());
//	    	product.setCustomerNotAuthority(cusnotAuth.get());
//	    }
//	    product.setProductStatus(true);
//		productRepository.save(product);		
//		return new ResponseEntity<>(product, HttpStatus.OK);
		/////////////////////////////////////////////////////
//		String idCard = finalProductAdmin.getIdCard();
//		String nameCustomer = finalProductAdmin.getNameCustomer();
//		String phoneNumber = finalProductAdmin.getPhoneNumber();
//		String dayOfBirth = finalProductAdmin.getDayOfBirth();
//		Date date = format.parse(dayOfBirth);
//		CustomerNotAuthority customerNotAuthority = new CustomerNotAuthority();
//		customerNotAuthority.setIdCard(idCard);
//		customerNotAuthority.setNameCustomer(nameCustomer);
//		customerNotAuthority.setPhoneNumber(phoneNumber);
//		customerNotAuthority.setDayOfBirth(date);
//		customerNotAuthorityRepository.save(customerNotAuthority);
//		product.setCustomerNotAuthority(customerNotAuthority);
//		product.setProductStatus(true);
//		productRepository.save(product);
		
		return new ResponseEntity<>("Da dang bai thanh cong!!!", HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////
	//Admin assign post final
	@PutMapping(path = "EditPostByAdminFinal/{id}")
	public ResponseEntity<Object> addPostByAdminFinal(@PathVariable("id") long id, @Valid @RequestBody FinalProductAdmin finalProductAdmin, BindingResult result) throws ParseException {
		Optional<Product> pro = productRepository.findById(id);
		if(!pro.isPresent()) {
			return new ResponseEntity<>("San pham nay khong ton tai...", HttpStatus.NOT_FOUND);
		}
		Product p = pro.get();
		long idBroker = finalProductAdmin.getBrokerId();
		Optional<Broker> bro = brokerRepository.findById(idBroker);
	    if(!bro.isPresent()) {
	    	return new ResponseEntity<>("Not found id of broker.", HttpStatus.NOT_FOUND);
	    }
	    Broker broker = bro.get();
	    p.setBroker(broker);
	    long customerId = finalProductAdmin.getCustomerId();
	    if(customerId == 0) {
	    	CustomerNotAuthority cus = new CustomerNotAuthority();
	    	String idCard = finalProductAdmin.getIdCard();
	    	String nameCustomer = finalProductAdmin.getNameCustomer();
			String phoneNumber = finalProductAdmin.getPhoneNumber();
			String dayOfBirth = finalProductAdmin.getDayOfBirth();
			String description = finalProductAdmin.getDescription();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyy-MM-dd HH:mm:ss");
			Date date = format.parse(dayOfBirth);
			cus.setDayOfBirth(date);
			cus.setDescription(description);
			cus.setIdCard(idCard);
			cus.setNameCustomer(nameCustomer);
			customerNotAuthorityRepository.save(cus);
			p.setCustomerNotAuthority(cus);
	    } else {
	    	Optional<CustomerNotAuthority> customerOptional = customerNotAuthorityRepository.findById(customerId);
	    	if(!customerOptional.isPresent()) {
	    		return new ResponseEntity<>("Error here...", HttpStatus.BAD_REQUEST);
	    	}
	    	CustomerNotAuthority customer = customerOptional.get();
	    	p.setCustomerNotAuthority(customer); 	 	
	    }
	    productRepository.save(p);
		return new ResponseEntity<>("Da dang bai dang cuoi cung cua Admin thanh cong!!!", HttpStatus.OK);
	}
	
	
	
	
	
	//test thu api
	@PutMapping(path = "EditPostByAdminFinal1")
	public ResponseEntity<Object> addPostByAdminFinal1(@Valid @RequestBody FinalProductAdmin finalProductAdmin, BindingResult result) {
		long id = finalProductAdmin.getCustomerId();
		String nameCustomer = finalProductAdmin.getNameCustomer();
		
		return new ResponseEntity<>(id + nameCustomer, HttpStatus.OK);
	}
	
	
	
	@PostMapping(path = "/addCustomers")
	public ResponseEntity<Object> addCustomers(@Valid @RequestBody CustomerNotAuthorityResponse customerNotAuthorityResponse, BindingResult result) throws ParseException {
	    String idCard = customerNotAuthorityResponse.getIdCard();
	    
	    Optional<CustomerNotAuthority> cus = customerNotAuthorityRepository.findByIdCard(idCard);
	    if(cus.isPresent()) {
	    	return new ResponseEntity<>("Da trung chinhs minh nhan dan", HttpStatus.NOT_FOUND);
	    }
	    CustomerNotAuthority customer = new CustomerNotAuthority();
	    String nameCustomer = customerNotAuthorityResponse.getNameCustomer();
	    String phoneNumber = customerNotAuthorityResponse.getPhoneNumber();
	    String description = customerNotAuthorityResponse.getDescription();
	    String dayOfBirth = customerNotAuthorityResponse.getDayOfBirth();
	    SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
	    Date date = format.parse(dayOfBirth);
	    customer.setIdCard(idCard);
	    customer.setNameCustomer(nameCustomer);
	    customer.setPhoneNumber(phoneNumber);
	    customer.setDescription(description);
	    customer.setDayOfBirth(date);
	    customerNotAuthorityRepository.save(customer); 
		return new ResponseEntity<>("Da them customer thanh cong", HttpStatus.OK);
	}
	
	@PutMapping(path = "/editCustomers/{id}")
	public ResponseEntity<Object> editCustomers(@PathVariable("id") Long id, @Valid @RequestBody CustomerNotAuthorityResponse customerNotAuthorityResponse, BindingResult result) throws ParseException {
	    String idCard = customerNotAuthorityResponse.getIdCard();
	    
	    //Optional<CustomerNotAuthority> cus = customerNotAuthorityRepository.findByIdCard(idCard);
	    Optional<CustomerNotAuthority> cus = customerNotAuthorityRepository.findById(id);
	    if(!cus.isPresent()) {
	    	return new ResponseEntity<>("Ban nhap sai khach hang roi...", HttpStatus.NOT_FOUND);
	    }
	    CustomerNotAuthority customer = cus.get();
	    String nameCustomer = customerNotAuthorityResponse.getNameCustomer();
	    String phoneNumber = customerNotAuthorityResponse.getPhoneNumber();
	    String description = customerNotAuthorityResponse.getDescription();
	    String dayOfBirth = customerNotAuthorityResponse.getDayOfBirth();
	    Boolean nonDel = customerNotAuthorityResponse.getNonDel();
	    SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
	    Date date = format.parse(dayOfBirth);
	    //customer.setIdCard(idCard);
	    customer.setNameCustomer(nameCustomer);
	    customer.setPhoneNumber(phoneNumber);
	    customer.setDescription(description);
	    customer.setDayOfBirth(date);
	    customer.setNonDel(nonDel);
	    customerNotAuthorityRepository.save(customer); 
		return new ResponseEntity<>("Da sua thanh cong", HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/customers/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id) {
		Optional<CustomerNotAuthority> cus = customerNotAuthorityRepository.findById(id);
		if(!cus.isPresent()) {
			return new ResponseEntity<>("NOT FOUND CUSTOMER WITH THIS ID", HttpStatus.NOT_FOUND);
		}
		CustomerNotAuthority customer = cus.get();
		customer.setNonDel(false);
		customerNotAuthorityRepository.save(customer);
		return new ResponseEntity<>("Da xoa customer thanh cong...", HttpStatus.OK);
	}
	
	@GetMapping(path = "/listProductCustomerIsNull")
	public ResponseEntity<Object> listProductCustomerIsNull() {
		List<Product> listProduct = productRepository.findByCustomerNotAuthorityIsNull();
		if(listProduct == null || listProduct.size() == 0) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> list = new ArrayList<>();
		ProductPublicResponse productPublicResponse;
		for(Product p: listProduct) {
			productPublicResponse = productService.convertToProductResponsePublic(p);
			list.add(productPublicResponse);
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
	/////////////////////
//	@Autowired
//	private CatalogRepository catalogRepository;
//	
//	@GetMapping(path = "/listCatalog")
//	public ResponseEntity<Object> listCatalog() {
//		List<Catalog> listRole = catalogRepository.findAll();
//		if(listRole == null || listRole.size() == 0) {
//			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<>(listRole, HttpStatus.OK);
//	}
//
	
	@GetMapping(path = "/getAllPostWithAdmin")
	public ResponseEntity<Object> getAllPostWithAdmin(Principal p) {
		
		String email = p.getName();
		List<Product> listProduct = productRepository.findByAdminUserEmailAndNonDel(email, true);
		if((listProduct.size() == 0)||(listProduct == null)) {
			return new ResponseEntity<>("Not found!!!", HttpStatus.NOT_FOUND);
		}
		List<ProductPublicResponse> listP = new ArrayList<>();
		ProductPublicResponse pro;
		for(Product product: listProduct) {
			pro = productService.convertToProductResponsePublic(product);
			listP.add(pro);
		}
		
		return new ResponseEntity<>(listP, HttpStatus.OK);
	}
	
	//Admin quản lý tất cả các owner trong hệ thống
	@GetMapping(path = "/listOwners")
public ResponseEntity<Object> getListOwner() {
		
//		String email = p.getName();
//		List<Product> listProduct = productRepository.findByAdminUserEmailAndNonDel(email, true);
//		if((listProduct.size() == 0)||(listProduct == null)) {
//			return new ResponseEntity<>("Not found!!!", HttpStatus.NOT_FOUND);
//		}
//		List<ProductPublicResponse> listP = new ArrayList<>();
//		ProductPublicResponse pro;
//		for(Product product: listProduct) {
//			pro = productService.convertToProductResponsePublic(product);
//			listP.add(pro);
//		}
		List<Owner> listOwner = ownerRepository.findAll();
		if((listOwner == null) || (listOwner.size() == 0)) {
			return new ResponseEntity<>("Not found any owner in system!!!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listOwner, HttpStatus.OK);
	}
	
	
	
	@PostMapping(path = "/assignBrokerWithOldOwner")
	public ResponseEntity<Object> assignBrokerWithOldOwner(@Valid @RequestBody AssignBrokerWithOldCustomerDTO  awcd, BindingResult result,  Principal p) throws JsonProcessingException {
		
//		AssignBrokerWithCustomerDTOResponse assignBrokerWithCustomerDTOResponse = new AssignBrokerWithCustomerDTOResponse();
//		if(result.hasErrors()){          
//	    	  System.out.println(result.getAllErrors().toString());
//	          Map<String, String> errors = result.getFieldErrors().stream()
//	                .collect(
//	                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
//	                  );	        
////	          if(result.getAllErrors().toString().indexOf("PasswordMatches")!= -1) {
////	        	  errors.put("matchingPassword", "Password is not matched");
////	          }c
//	          assignBrokerWithCustomerDTOResponse.setValidated(false);
//	          assignBrokerWithCustomerDTOResponse.setErrorMessages(errors); 
////	          ObjectMapper mapper = new ObjectMapper();
////	          String json = mapper.writeValueAsString(assignBrokerWithCustomerDTOResponse);
////	          return new ResponseEntity<Object>(json, HttpStatus.OK);
//	          //return new ResponseEntity<>(assignBrokerWithCustomerDTOResponse, HttpStatus.OK);
//	          
//	          Map<String, String> map = assignBrokerWithCustomerDTOResponse.getErrorMessages();
//	          
//	          Set set=map.entrySet();//Converting to Set so that we can traverse  
//	          Iterator itr=set.iterator(); 
//	          String message = "";
//	          while(itr.hasNext()){  
//	              //Converting to Map.Entry so that we can get key and value separately  
//	              Map.Entry entry=(Map.Entry)itr.next();  
//	              //System.out.println(entry.getKey()+" "+entry.getValue());  
//	             message  = message + " - " + entry.getValue();
//	          }  
//	          
//	          return new ResponseEntity<Object>(message, HttpStatus.OK);
//	      }
		
		String email = p.getName();
		Optional<User> u = userRepository.findByEmail(email);
		if(!u.isPresent()) {
			return new ResponseEntity<>("Sai quyen admin roi", HttpStatus.OK);
		}
		User user = u.get();
		Admin admin = user.getAdmin();
		//String ownerName = awcd.getOwnerName();
		long broker_id = awcd.getBrokerId();
		Optional<Broker> bro = brokerRepository.findById(broker_id);
		if(!bro.isPresent()) {
			return new ResponseEntity<>("Not Found This Broker", HttpStatus.OK);
		}
		Broker broObject = bro.get();
		Optional<Owner> ownerOptional = ownerRepository.findById(awcd.getOwnerId());
		if(!ownerOptional.isPresent()) {
			return new ResponseEntity<>("Not found this owner!!!", HttpStatus.NOT_FOUND);
		}
		Owner owner = ownerOptional.get();
		//Owner owner = new Owner();
		//owner.setOwnerName(ownerName);
		//owner.setIdCard(awcd.getIdCard());
		//owner.setPhoneNumber(awcd.getPhoneNumber());
		//owner.setAddress(awcd.getAddress());
		owner.setBroker(broObject);
		owner.setAdmin(admin);
		
		
		ownerRepository.save(owner);
		return new ResponseEntity<>("Da chi dinh thanh cong Broker voi chu san pham thanh cong!!!", HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}












