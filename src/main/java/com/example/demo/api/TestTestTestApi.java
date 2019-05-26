package com.example.demo.api;

import java.text.ParseException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TestTestTest;
import com.example.demo.entity.dto.TestThuDTO;
import com.example.demo.repository.TestTestTestRepository;

@RestController
@RequestMapping("/test")
public class TestTestTestApi {
	
	@Autowired
	private TestTestTestRepository tttp;
	
	@PostMapping(path = "/addTest")
	public ResponseEntity<Object> addUser(@Valid @RequestBody TestThuDTO testThuDTO, BindingResult result) throws ParseException {
				
		TestTestTest obj = new TestTestTest();
		obj.setEmail(testThuDTO.getB());
		obj.setActive(testThuDTO.getActive());
		tttp.save(obj);
		return new ResponseEntity<>("Da them test thanh cong", HttpStatus.OK);
	}
	
}
