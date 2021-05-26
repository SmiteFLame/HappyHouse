package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.LoginDao;
import com.ssafy.happyhouse.dto.MemberDto;

@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(Map<String, String> map) throws Exception {
		if(map.get("userid") == null || map.get("userpwd") == null)
			return null;
		return sqlSession.getMapper(LoginDao.class).login(map);
	}

	@Override
	public List<MemberDto> userList() {
		return sqlSession.getMapper(LoginDao.class).userList();
	}

	@Override
	public MemberDto userInfo(String userid) {
		return sqlSession.getMapper(LoginDao.class).userInfo(userid);
	}


	@Override
	public void userModify(MemberDto memberDto) {
		sqlSession.getMapper(LoginDao.class).userModify(memberDto);
	}

	@Override
	public void userDelete(String userid) {
		sqlSession.getMapper(LoginDao.class).userDelete(userid);
	}

	@Override
	public void userRegister(MemberDto memberDto) {
		sqlSession.getMapper(LoginDao.class).userRegister(memberDto);
		}

}
