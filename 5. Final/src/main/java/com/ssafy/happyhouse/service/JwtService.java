package com.ssafy.happyhouse.service;

import java.util.Map;

import com.ssafy.happyhouse.dto.MemberDto;

public interface JwtService {
	public String create(final MemberDto member);
	public void checkValid(final String jwt);
	public Map<String, Object> get(final String jwt);	
}
