package com.example.demo.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Broker;
import com.example.demo.entity.CatalogDetail;
import com.example.demo.entity.Currency;
import com.example.demo.entity.DBFile;
import com.example.demo.entity.DBFileProfile;
import com.example.demo.entity.Owner;
import com.example.demo.entity.Product;
import com.example.demo.entity.dto.NewPostDTO;
import com.example.demo.repository.BrokerRepository;
import com.example.demo.repository.CatalogDetailRepository;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.DBFileProfileRepository;
import com.example.demo.repository.DBFileRepository;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.DBFileProfileService;
import com.example.demo.service.DBFileStorageService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService DBFileStorageService;
    
    @Autowired
    private BrokerRepository brokerRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CatalogDetailRepository catalogDetailRepository;
    @Autowired
    private DBFileRepository dBFileRepository;
    
    @Autowired
    private DBFileProfileService dBFileProfileService;
    
    @Autowired
    private DBFileProfileRepository dBFileProfileRepository;

    @PostMapping("/uploadFile")
    //UploadFileResponse
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("newPost") String newPost) throws JsonParseException, JsonMappingException, IOException {
        DBFile dbFile = DBFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId().toString())
                .toUriString();
        
        //sua tai day
        //NewPostDTO a = new NewPostDTO();
        ObjectMapper mapper = new ObjectMapper();
        NewPostDTO newPostDTO = mapper.readValue(newPost, NewPostDTO.class);
        
//        DBFile m = new DBFile();
//        m.setId(10L);
//        m.setFileName("ghghg.jpg");
        dBFileRepository.save(dbFile);
        
        Product product = new Product();
        product.setdBFile(dbFile);
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
		
		productRepository.save(product);
	
        
        return new ResponseEntity<>(product, HttpStatus.OK);
        
        
        
        
        
        
        
        
        
        
        //
//        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
    }
    
    
    
    
    
//    @PostMapping("/editUploadFile/{id}")
    @PostMapping("/editUploadFile/{id}")
    //UploadFileResponse
    public ResponseEntity<Object> editUploadFile(@PathVariable("id") long id, @RequestParam("file") MultipartFile file, @RequestParam("newPost") String newPost) throws JsonParseException, JsonMappingException, IOException {
        
    	Optional<Product> p = productRepository.findById(id);
    	if(!p.isPresent()) {
    		return new ResponseEntity<>("Not found product have this id.", HttpStatus.NOT_FOUND);
    	}
    	
    	Product product = p.get();
    	
    	DBFile dbFile = DBFileStorageService.storeFile(file);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId().toString())
                .toUriString();
        
        //sua tai day
        //NewPostDTO a = new NewPostDTO();
        ObjectMapper mapper = new ObjectMapper();
        NewPostDTO newPostDTO = mapper.readValue(newPost, NewPostDTO.class);
        
//        DBFile m = new DBFile();
//        m.setId(10L);
//        m.setFileName("ghghg.jpg");
        dBFileRepository.save(dbFile);
        
        
        product.setdBFile(dbFile);
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
		
		productRepository.save(product);
	
        
        return new ResponseEntity<>(product, HttpStatus.OK);
        
        
        
        
        
        
        
        
        
        
        //
//        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

//    @GetMapping("/downloadFile/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
//        // Load file from database
//        DBFile dbFile = DBFileStorageService.getFile(fileId);
//        //return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType())).body(new ByteArrayResource(dbFile.getData()));
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
//                .body(new ByteArrayResource(dbFile.getData()));
//    }
      
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        DBFile dbFile = DBFileStorageService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType())).body(new ByteArrayResource(dbFile.getData()));

//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName()  + "\"")
//                .body(new ByteArrayResource(dbFile.getData()));
            
    }
    
    @GetMapping("/downloadFileProfile/{fileId}")
    public ResponseEntity<Resource> downloadFileProfile(@PathVariable Long fileId) {
        // Load file from database
        DBFileProfile dbFile = dBFileProfileService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType())).body(new ByteArrayResource(dbFile.getData()));

//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
//                .body(new ByteArrayResource(dbFile.getData()));
            
    }
    
    //code upload file
    
    @PostMapping("/uploadOneFile")
    //UploadFileResponse
    public ResponseEntity<Object> uploadOneFile( @RequestParam("file") MultipartFile file) throws JsonParseException, JsonMappingException, IOException {
        
//    	Optional<Product> p = productRepository.findById(id);
//    	if(!p.isPresent()) {
//    		return new ResponseEntity<>("Not found product have this id.", HttpStatus.NOT_FOUND);
//    	}
//    	
//    	Product product = p.get();
    	
    	DBFile dbFile = DBFileStorageService.storeFile(file);
    	dBFileRepository.save(dbFile);
    	return new ResponseEntity<>(dbFile, HttpStatus.OK);
    }
    
    
    @PostMapping("/uploadOneFileProfile")
    //UploadFileResponse
    public ResponseEntity<Object> uploadOneFileProfile( @RequestParam("file") MultipartFile file) throws JsonParseException, IOException {
        
//    	Optional<Product> p = productRepository.findById(id);
//    	if(!p.isPresent()) {
//    		return new ResponseEntity<>("Not found product have this id.", HttpStatus.NOT_FOUND);
//    	}
//    	
//    	Product product = p.get();
    	
    	DBFileProfile dBFileProfile = dBFileProfileService.storeFile(file);
    	dBFileProfileRepository.save(dBFileProfile);
    	return new ResponseEntity<>(dBFileProfile, HttpStatus.OK);
    }
    
    
    
//    @GetMapping("/downloadFile")
//    public List<ResponseEntity<Resource>> downloadFile() {
//        // Load file from database
//        //DBFile dbFile = DBFileStorageService.getFile(fileId);
//    	List<DBFile> dbFile = dBFileRepository.findAll();
//        //return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType())).body(new ByteArrayResource(dbFile.getData()));
//    	List<ResponseEntity<Resource>> list = new ArrayList<>();
//    	for(DBFile a: dbFile) {
//    		list.add(ResponseEntity.ok().contentType(MediaType.parseMediaType(a.getFileType())).body(new ByteArrayResource(a.getData())));
//    	}
//    	return list;
//
//}
    
}
