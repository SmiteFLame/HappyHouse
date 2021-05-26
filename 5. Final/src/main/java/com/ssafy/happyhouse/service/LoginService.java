package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.MemberDto;

public interface LoginService {
	public MemberDto login(Map<String, String> map) throws Exception;
	
//	REST 
	public List<MemberDto> userList();
	public MemberDto userInfo(String userid);
	public void userRegister(MemberDto memberDto);
	public void userModify(MemberDto memberDto);
	public void userDelete(String userid);
}
