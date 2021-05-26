package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.LoginService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/member")
public class LoginController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private LoginService service;

	@Autowired
	private JwtService jservice;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto member, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", member.getUserid());
			map.put("userpwd", member.getUserpwd());
			MemberDto memberDto = service.login(map);

			if (memberDto != null) {
				// 로그인 성공시 토큰을 생성

				// 오류 발생
				String token = jservice.create(memberDto);

				// 토큰 정보는 request의 헤더로 보내고 나머지는 Map에 담아주자.
				response.setHeader("jst-auth-token", token);

				result.put("status", true);
				result.put("data", memberDto);
				status = HttpStatus.ACCEPTED;

			} else {
				result.put("status", false);
				result.put("message", "로그인 실패");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", "로그인 실패");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		System.out.println(memberDto.getEmailid());
		try {
			service.userRegister(memberDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping()
	public ResponseEntity<List<MemberDto>> member() {
		return new ResponseEntity<List<MemberDto>>(service.userList(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody MemberDto member) {
		try {
			service.userModify(member);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		try {
			service.userDelete(id);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
//	
//	
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(MemberDto memberDto, Model model, HttpSession session) {
//			memberDto.setUserid(memberDto.getUserid());
//			try {
//				service.userModify(memberDto);
//				model.addAttribute("container", "updatesuccess");
//				return "index";
//			} catch (Exception e) {
//				e.printStackTrace();
//				model.addAttribute("msg", "회원정보 수정 중 문제가 발생했습니다.");
//				return "error/error";
//			}
//	}
//	
//
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//	
//	
//	@RequestMapping(value = "/remove", method = RequestMethod.POST)
//	public String remove(MemberDto memberDto, Model model, HttpSession session) {
//		
//			memberDto.setUserid(memberDto.getUserid());
//			try {
//				service.userDelete(memberDto.getUserid());
//				model.addAttribute("container", "deletesuccess");
//				return "index";
//			} catch (Exception e) {
//				e.printStackTrace();
//				model.addAttribute("msg", "회원정보 삭제 중 문제가 발생했습니다.");
//				return "error/error";
//			}
//	}

}
