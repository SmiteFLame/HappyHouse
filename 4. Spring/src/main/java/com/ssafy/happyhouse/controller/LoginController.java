package com.ssafy.happyhouse.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@GetMapping("loginPage")
	public String loginPage(Model model) {
		model.addAttribute("container", "login");
		return "index";
	}
	
	@GetMapping("joinPage")
	public String joinPage(Model model) {
		model.addAttribute("container", "join");
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("container", "login");
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) {
		try {
			MemberDto memberDto = service.login(map);
			if(memberDto != null) {
				session.setAttribute("userinfo", memberDto);
				
				Cookie cookie = new Cookie("ssafy_id", memberDto.getUserid());
				cookie.setPath("/");
				if("saveok".equals(map.get("idsave"))) {
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "index";
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		model.addAttribute("container", "join");
		return "index";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(MemberDto memberDto, Model model, HttpSession session) {
			memberDto.setUserid(memberDto.getUserid());
			try {
				service.userRegister(memberDto);
				model.addAttribute("container", "joinsuccess");
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "회원가입 중 문제가 발생했습니다.");
				return "error/error";
			}
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model) {
		model.addAttribute("container", "update");
		return "index";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MemberDto memberDto, Model model, HttpSession session) {
			memberDto.setUserid(memberDto.getUserid());
			try {
				service.userModify(memberDto);
				model.addAttribute("container", "updatesuccess");
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "회원정보 수정 중 문제가 발생했습니다.");
				return "error/error";
			}
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "container/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(MemberDto memberDto, Model model, HttpSession session) {
		
			memberDto.setUserid(memberDto.getUserid());
			try {
				service.userDelete(memberDto.getUserid());
				model.addAttribute("container", "deletesuccess");
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "회원정보 삭제 중 문제가 발생했습니다.");
				return "error/error";
			}
	}
	
	
	
	
	
	
	
	
	
	
}
