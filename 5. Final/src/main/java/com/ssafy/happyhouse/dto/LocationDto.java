package com.ssafy.happyhouse.dto;

public class LocationDto {
	private String dong;
	private double lat;
	private double lng;

	public LocationDto(String dong, double lat, double lng) {
		this.dong = dong;
		this.lat = lat;
		this.lng = lng;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}
