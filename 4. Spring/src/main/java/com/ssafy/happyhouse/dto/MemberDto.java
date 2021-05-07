package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto : 회원정보", description = "회원의 아이디, 이름, 비번, 이메일을 설정합니다.")
public class MemberDto {

	@ApiModelProperty(value = "회원 아이디")
	private String userid;
	@ApiModelProperty(value = "회원 이름")
	private String username;
	@ApiModelProperty(value = "비밀번호")
	private String userpwd;
	@ApiModelProperty(value = "이메일 주소")
	private String emailid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	

}

