package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.DealDto;
import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.service.BoardService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/board")
public class BoardController {
	

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	BoardService bService;

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
	public ResponseEntity<List<BoardDto>> board() {
		return new ResponseEntity<List<BoardDto>>(bService.selectAll(pageBean), HttpStatus.OK);
	}

	@GetMapping("{no}")
	public ResponseEntity<BoardDto> searchBoard(@PathVariable int no) {
		return new ResponseEntity<BoardDto>(bService.select(no), HttpStatus.OK);
	}
	
	
	@PostMapping()
	public ResponseEntity<String> writeBoard(@RequestBody BoardDto board) {
		if(bService.insertBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	
	@PutMapping()
	public ResponseEntity<String> updateBoard(@RequestBody BoardDto board) {
		if(bService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
		if(bService.deleteBoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
