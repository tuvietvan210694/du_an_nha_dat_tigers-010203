package com.example.demo.api;

import com.example.demo.entity.Currency;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.CurrencyRepository;

@RestController
public class CurrencyApi {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@GetMapping(path = "/listCurrencys")
	public ResponseEntity<Object> listCurrencys() {
		List<Currency> list = currencyRepository.findAll();
		if((list == null) || (list.size() == 0)) {
			return new ResponseEntity<>("List currencys is empty...", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
