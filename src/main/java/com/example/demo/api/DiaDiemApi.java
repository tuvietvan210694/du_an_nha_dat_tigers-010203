package com.example.demo.api;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.City;
import com.example.demo.entity.District;
import com.example.demo.entity.Ward;
import com.example.demo.entity.dto.DiaDiemDTO;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.WardRepository;


@RestController
@RequestMapping("/position")
public class DiaDiemApi {
	
	@Autowired
	private CityRepository cityRepository;	
	
	@Autowired
	
	private DistrictRepository districtRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	
	@GetMapping(path = "/listCity")
	public ResponseEntity<Object> displayListCity() {
		List<City> listCity = cityRepository.findAll();
		if(listCity.size() == 0) {
			return new ResponseEntity<>("List all citys is empty", HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<>(listCity, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listCity1")
	public ResponseEntity<Object> displayListCity1() {
		List<City> listCity = cityRepository.findListCity();
		if(listCity.size() == 0) {
			return new ResponseEntity<>("List all citys is empty!!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listCity, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listCity2")
	public ResponseEntity<Object> displayListCity2() {
		List<City> listCity = cityRepository.findListCity2();
		if(listCity.size() == 0) {
			return new ResponseEntity<>("List all citys is empty!!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listCity, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listCity3")
	public ResponseEntity<Object> displayListCity3() {
		List<City> listCity = cityRepository.findListCity3("01");
		if(listCity.size() == 0) {
			return new ResponseEntity<>("List all citys is empty!!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listCity, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listDistrictOfDaNang")
	public ResponseEntity<Object> displayListDistrictOfDaNang() {
		List<Object> listCity = cityRepository.findListDistrictOfDaNang();
		if(listCity.size() == 0) {
			return new ResponseEntity<>("List all citys is empty!!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listCity, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/city/{id}")
	public ResponseEntity<Object> displayListDistrict(@PathVariable("id") long id) {
		Optional<City> city = cityRepository.findById(id);
		if(city == null) {
			return new ResponseEntity<>("Not Found any city", HttpStatus.NOT_FOUND);
		}
		List<District> listDistrict = city.get().getListDistrict();
		if(listDistrict.size() == 0) {
			return new ResponseEntity<>("Not found any district in one city", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listDistrict, HttpStatus.OK);
	}
	
	@GetMapping(path = "city/district/{id}")
	public ResponseEntity<Object> displayListWard(@PathVariable("id") long id) {
		Optional<District> district = districtRepository.findById(id);
		if(district == null) {
			return new ResponseEntity<>("Not found any district", HttpStatus.NOT_FOUND);
		}
		List<Ward> listWard = district.get().getListWard();
		if(listWard.size() == 0) {
			return new ResponseEntity<>("Not found any ward in one district", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listWard, HttpStatus.OK);		
	}
	
	
	@GetMapping(path = "city/district/ward/{id}")
	public ResponseEntity<Object> displayWard(@PathVariable("id") long id) {
		Optional<Ward> ward = wardRepository.findById(id);
		if(!ward.isPresent()) {
			return new ResponseEntity<>("Not found any ward", HttpStatus.NOT_FOUND);
		}
		Ward w = ward.get();
		String city = w.getDistrict().getCity().getMaCity();
		String district = w.getDistrict().getMaDistrict();
		String wa = w.getMaWard();
		String sequence = "";
	    sequence = city + district +wa;
		return new ResponseEntity<>(sequence, HttpStatus.OK);		
	}
	
	@GetMapping(path = "/city/name1")
	public ResponseEntity<Object> displayDistrict(@RequestParam("ma") String ma) {
		Optional<City> city = cityRepository.findByMaCity(ma);
		if(!city.isPresent()) {
			return new ResponseEntity<>("Not found city theo ma", HttpStatus.NOT_FOUND);
		}
		City c = city.get();
		List<District> listD = c.getListDistrict();
		//String nameDistrict = d.getNameDistrict();
		return new ResponseEntity<>(listD, HttpStatus.OK);		
	}
	
	//@GetMappint(path = "/city/ditrict/name1")
	
	@GetMapping(path = "/city/district/name1")
	public ResponseEntity<Object> displayWard(@RequestParam("ma") String ma) {
		Optional<District> district = districtRepository.findByMaDistrict(ma);
		if(!district.isPresent()) {
			return new ResponseEntity<>("Not found district theo name", HttpStatus.NOT_FOUND);
		}
		District d = district.get();
		//String nameDistrict = d.getNameDistrict();
		List<Ward> listW = d.getListWard();
		return new ResponseEntity<>(listW, HttpStatus.OK);		
	}
	
	
	
	@GetMapping(path = "/listDistrict")
	public ResponseEntity<Object> displayListDistrict() {
		List<District> listDistrict = districtRepository.findAll();
		if(listDistrict.size() == 0) {
			return new ResponseEntity<>("Not found any district", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listDistrict, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listWard")
	public ResponseEntity<Object> displayListWard() {
		List<Ward> listWard = wardRepository.findAll();
		if(listWard.size() == 0) {
			return new ResponseEntity<>("Not found any ward", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listWard, HttpStatus.OK);
	}
	
}
