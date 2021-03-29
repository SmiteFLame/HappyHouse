package com.ssafy.environment;

public class EnvironmentInfo {
	private String facName;				//업체(시설)명
	private String licenseNum;			//인허가번호 
	private String businessCode;		//업종코드 
	private String businessName;		//업종명 
	private String checkDate;			//지도점검일자
	private String checkAgency;			//점김기관 
	private String checkArea;			//점검기관명 
	private String regularCheck;		//지도점검구분
	private String disposition;			//처분대상여부 
	private String checkOption;			//점검사항
	private String result;				//점검결과
	private String streetAddress;		//소재지도로명주소 
	private String address;				//소재지주소 
	public String getFacName() {
		return facName;
	}
	public void setFacName(String facName) {
		this.facName = facName;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getArea() {
		return checkArea;
	}
	public void setArea(String area) {
		this.checkArea = area;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckAgency() {
		return checkAgency;
	}
	public void setCheckAgency(String checkAgency) {
		this.checkAgency = checkAgency;
	}
	public String getCheckArea() {
		return checkArea;
	}
	public void setCheckArea(String checkArea) {
		this.checkArea = checkArea;
	}
	public String getRegularCheck() {
		return regularCheck;
	}
	public void setRegularCheck(String regularCheck) {
		this.regularCheck = regularCheck;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getCheckOption() {
		return checkOption;
	}
	public void setCheckOption(String checkOption) {
		this.checkOption = checkOption;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "EnvironmentInfo [facName=" + facName + ", licenseNum=" + licenseNum + ", businessCode=" + businessCode
				+ ", businessName=" + businessName + ", checkDate=" + checkDate + ", checkAgency=" + checkAgency
				+ ", checkArea=" + checkArea + ", regularCheck=" + regularCheck + ", disposition=" + disposition
				+ ", checkOption=" + checkOption + ", result=" + result + ", streetAddress=" + streetAddress
				+ ", address=" + address + "]";
	}

	
	
}
