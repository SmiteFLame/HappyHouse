package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.MemberDto;

public interface LoginDao {
	public MemberDto login(Map<String, String> map) throws SQLException;
	
//	REST
	public List<MemberDto> userList();
	public MemberDto userInfo(String userid);
	public int userRegister(MemberDto memberDto);
	public int userModify(MemberDto memberDto);
	public int userDelete(String userid);
}
