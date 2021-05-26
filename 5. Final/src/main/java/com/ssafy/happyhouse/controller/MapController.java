package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.AddressDto;
import com.ssafy.happyhouse.dto.DealDto;
import com.ssafy.happyhouse.dto.LocationDto;
import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.service.DealService;

@RestController
@RequestMapping("/api/map")
public class MapController {

	@Autowired
	DealService dService;
	
	PageBean pageBean;
	
	@PostConstruct
	public void init() {
		pageBean = new PageBean("", "", 1);
	}
	
	@GetMapping("/setbean/{no}")
	public void setBean(@PathVariable int no) {
		pageBean.setPageNo(no);
	}
	
	@GetMapping("/bean")
	public ResponseEntity<PageBean> pageBean(){
		return new ResponseEntity<PageBean>(pageBean, HttpStatus.OK);
	}
	
	@GetMapping("/bean/all")
	public void setBean(@PathVariable String key){
		pageBean.setKey(key);
	}
	
	@GetMapping("/bean/{key}/{word}")
	public void setBeanWithWord(@PathVariable String key, @PathVariable String word){
		pageBean.setKey(key);
		pageBean.setWord(word);
	}
	
	@GetMapping
	public ResponseEntity<List<DealDto>> deal() {
		return new ResponseEntity<List<DealDto>>(dService.selectAll(pageBean), HttpStatus.OK);
	}



	@GetMapping("{no}")
	public ResponseEntity<DealDto> dealno(@PathVariable int no) {
		return new ResponseEntity<DealDto>(dService.select(no), HttpStatus.OK);
	}

	@GetMapping("/location/{no}")
	public ResponseEntity<LocationDto> dealloc(@PathVariable int no) {
		return new ResponseEntity<LocationDto>(dService.getLocation(no), HttpStatus.OK);
	}

	@GetMapping("near/{table}/{dong}")
	public ResponseEntity<List<AddressDto>> findnear(@PathVariable String table, @PathVariable String dong){
		return new ResponseEntity<List<AddressDto>>(dService.findnear(table, dong), HttpStatus.OK); 
	}
 

//	@GetMapping("mall")
//	public String mall(Model model) {
//		model.addAttribute("container", "dataList");
//		model.addAttribute("model", "mall");
//		return "index";
//	}
//
//	@GetMapping("corona")
//	public String corona(Model model) {
//		model.addAttribute("container", "dataList");
//		model.addAttribute("model", "corona");
//		return "index";
//	}
}
