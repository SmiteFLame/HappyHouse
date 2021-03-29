package com.ssafy.happyhouse.Mall.dto;

public class MallInfo {

	/** 상가를 식별할 번호 */
	private int no;
	/** 상가명 기존 이름 */
	private String shopName;
	/** 상가명 로컬 이름 */
	private String localName;
	/** 상가 코드3 */
	private String code3;
	/** 상가 코드4 */
	private String code4;
	/** 도시 이름 */
	private String cityName;
	/** 구 이름  */
	private String guName;
	/** 동 이름  */
	private String dongName;
	/** 지번 주소 */
	private String jibuaddress;
	/** 도로명 주소 */
	private String doroaddress;


	private String lat;
	private String lng;

	public MallInfo() {
	}

	public MallInfo(int no) {
		super();
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getCode4() {
		return code4;
	}

	public void setCode4(String code4) {
		this.code4 = code4;
	}



	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGuName() {
		return guName;
	}

	public void setGuName(String guName) {
		this.guName = guName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getJibuaddress() {
		return jibuaddress;
	}

	public void setJibuaddress(String jibuaddress) {
		this.jibuaddress = jibuaddress;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getDoroaddress() {
		return doroaddress;
	}

	public void setDoroaddress(String doroaddress) {
		this.doroaddress = doroaddress;
	}

	@Override
	public String toString() {
		return "MallInfo [no=" + no + ", shopName=" + shopName + ", localName=" + localName + ", code3=" + code3
				+ ", code4=" + code4 + ", cityName=" + cityName + ", guName=" + guName + ", dongName=" + dongName
				+ ", jibuaddress=" + jibuaddress + ", doroaddress=" + doroaddress + ", lat=" + lat + ", lng=" + lng
				+ "]";
	}
	

}
