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

import com.ssafy.happyhouse.dto.QnaDto;
import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.dto.QnaDto;
import com.ssafy.happyhouse.service.QnaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/qna")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaService qService;
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
	public ResponseEntity<List<QnaDto>> board() {
		return new ResponseEntity<List<QnaDto>>(qService.retrieveQna(), HttpStatus.OK);
	}

	
//	@PostMapping()
//	public ResponseEntity<String> writeBoard(@RequestBody QnaDto board) {
//		if(qService.insertBoard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
//
//	
//	@PutMapping()
//	public ResponseEntity<String> updateBoard(@RequestBody QnaDto board) {
//		if(qService.updateBoard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
//	
//	@DeleteMapping("{no}")
//	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
//		if(qService.deleteBoard(no)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
}