package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.service.DealService;

@Controller
public class MapController {

	@Autowired
	DealService dService;
	
	PageBean tempBean;

	@GetMapping("deal")
	public String deal(Model model, @ModelAttribute("bean") PageBean bean) {
		model.addAttribute("container", "dataList");
		model.addAttribute("model", "deal");
		model.addAttribute("bean", bean);
		model.addAttribute("deallist", dService.selectAll(bean));
		// 이전 검색어, 단어, 페이지 미리 저장해 놓기

		tempBean = bean;
		
		return "index";
	}

	@GetMapping("dealno")
	public String dealno(Model model, int no) {
		model.addAttribute("container", "dataList");
		model.addAttribute("model", "deal");
		model.addAttribute("deallist", dService.selectAll(tempBean));
		model.addAttribute("dealone", dService.select(no));
		model.addAttribute("bean", tempBean);
		model.addAttribute("location", dService.getLocation(no));

		// 이전 검색어, 단어, 페이지 미리 저장해 놓기
		return "index";
	}

	@GetMapping("mall")
	public String mall(Model model) {
		model.addAttribute("container", "dataList");
		model.addAttribute("model", "mall");
		return "index";
	}

	@GetMapping("corona")
	public String corona(Model model) {
		model.addAttribute("container", "dataList");
		model.addAttribute("model", "corona");
		return "index";
	}
}
