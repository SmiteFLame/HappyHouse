package com.ssafy.happyhouse.Mall.dto;

import java.util.Arrays;

public class MallPageBean {
	
	private boolean[] searchType;
	
	private String  city;
	private String  gu;
	


	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	
	public boolean[] getSearchType() {
		return searchType;
	}
	public void setSearchType(boolean[] searchType) {
		this.searchType = searchType;
	}
	
	@Override
	public String toString() {
		return "MallPageBean [searchType=" + Arrays.toString(searchType) + ", city=" + city + ", gu=" + gu + "]";
	}

}
