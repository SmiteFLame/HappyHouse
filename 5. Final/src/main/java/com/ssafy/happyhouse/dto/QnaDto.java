package com.ssafy.happyhouse.dto;

import java.util.Date;

public class QnaDto {
	private int no;
	private String title;
	private String question;
	private String answer;
	private String id;
	private Date regtime;

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	
	@Override
	public String toString() {
		return "QnaDto [no=" + no + ", title=" + title + ", question=" + question + ", answer=" + answer + ", id="
				+ id + ", regtime=" + regtime + "]";
	}
	
	
	
}	