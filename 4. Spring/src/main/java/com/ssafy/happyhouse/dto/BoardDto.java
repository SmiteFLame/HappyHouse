package com.ssafy.happyhouse.dto;

public class BoardDto {
	private int no;
	private String name;
	private String info;
	private String title;
	
	public BoardDto(int no, String name, String info, String title) {
		super();
		this.no = no;
		this.name = name;
		this.info = info;
		this.title = title;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
