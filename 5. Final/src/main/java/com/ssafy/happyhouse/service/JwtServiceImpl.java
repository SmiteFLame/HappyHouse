package com.ssafy.happyhouse.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.dto.MemberDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.expmin}")
	private Long expireMin;

	@Override
	public String create(MemberDto member) {
		final JwtBuilder builder = Jwts.builder();

		// Header 설정
		builder.setHeaderParam("typ", "JWT");

		// payload 설정
		builder.setSubject("로그인 토큰").setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
				.claim("Member", member);

		// signature - serect key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

		// 마지막 직렬화
		final String jwt = builder.compact();
		return jwt;
	}

	@Override
	public void checkValid(String jwt) {
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);

	}

	@Override
	public Map<String, Object> get(String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		}catch (Exception e) {
			throw new RuntimeException();
		}
		return claims.getBody();
	}

}
