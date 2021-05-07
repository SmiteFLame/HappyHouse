package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bService;

	PageBean tempBean;
	
	@GetMapping("boardlist")
	public String listPage(Model model, @ModelAttribute("bean") PageBean bean) {
		model.addAttribute("container", "boardlist");
		model.addAttribute("board", bService.selectAll(bean));
		model.addAttribute("bean", bean);
		
		tempBean = bean;
		
		return "index";
	}
	
	@GetMapping("listno")
	public String listno(Model model, int no) {
		model.addAttribute("container", "listone");
		model.addAttribute("listone", bService.select(no));
		return "index";
	}
	
	@GetMapping("write")
	public String listinsert(Model model) {
		model.addAttribute("container", "write");
		return "index";
	}

	@GetMapping("sitemap")
	public String sitemap(Model model) {
		model.addAttribute("container", "sitemap");
		return "index";
	}
}
