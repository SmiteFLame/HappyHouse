package com.ssafy.happyhouse.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.happyhouse.Mall.dto.MallInfo;

/**
 * FoodNutritionSAXHandler와 FoodSAXHandler를 이용해서 식품 정보를 load하는 SAX Parser 프로 그램  
 *
 */
public class MallSaxParser {
	private List<MallInfo> mallInfo;
	private int size;
	/**
	 * 아파트 거래 정보를 식별하기 위한 번호로 차후 DB에서는 primary key로 대체하지만 
	 * 현재 버전에서는 0번부터 순차 부여한다. 
	 */
	public static int no;
 	public MallSaxParser() {
		loadData();
	}
 	
 	/**
 	 * HouseSAXHandler를 이용 파싱한 아파트 거래 내역을 추출한다. 
 	 */
	private void loadData() {	
		try{
			MallSAXHandler handler = new MallSAXHandler();
			mallInfo = handler.getMallInfo();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<MallInfo> getMallInfo() {
		return mallInfo;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMallInfo(List<MallInfo> mallInfo) {
		this.mallInfo = mallInfo;
	}

	public static void main(String[] args) {
		new MallSaxParser();
	}
}
