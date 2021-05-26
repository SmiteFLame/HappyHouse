package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.dto.CommunityDto;
import com.ssafy.happyhouse.dto.CommunityDto;
import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.service.CommunityService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/community")
public class CommunityController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommunityService cService;
	
	PageBean pageBean;
	
	@PostConstruct
	public void init() {
		pageBean = new PageBean("", "", 1);
	}
	
	
	@GetMapping("/bean")
	public ResponseEntity<PageBean> pageBean(){
		return new ResponseEntity<PageBean>(pageBean, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CommunityDto>> Community() {
		return new ResponseEntity<List<CommunityDto>>(cService.retrieveCommunity(pageBean), HttpStatus.OK);
	}

	@GetMapping("{no}")
	public ResponseEntity<CommunityDto> searchCommunity(@PathVariable int no) {
		return new ResponseEntity<CommunityDto>(cService.detailCommunity(no), HttpStatus.OK);
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
	
	@GetMapping("/comment/{cno}")
	public ResponseEntity<List<CommentDto>> searchComments(@PathVariable int cno) {
		return new ResponseEntity<List<CommentDto>>(cService.selectComments(cno), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<String> writeCommunity(@RequestBody CommunityDto Community) {
		if(cService.writeCommunity(Community)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping()
	public ResponseEntity<String> updateCommunity(@RequestBody CommunityDto Community) {
		if(cService.updateCommunity(Community)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteCommunity(@PathVariable int no) {
		if(cService.deleteCommunity(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<String> writeComment(@RequestBody CommentDto comment) {
		if(cService.insertComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/comment/{no}")
	public ResponseEntity<String> deleteComment(@PathVariable int no) {
		if(cService.deleteComment(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}