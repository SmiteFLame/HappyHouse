package com.ssafy.happyhouse.Mall.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.Mall.dto.MallInfo;
import com.ssafy.happyhouse.Mall.dto.MallPageBean;
import com.ssafy.happyhouse.util.MallSaxParser;

public class MallDaoImpl implements MallDao{
	
	private List<MallInfo> MallInfo;
	private int size;
	private List<MallInfo> search;
	public MallDaoImpl() {
		loadData();
	}
	
	/**
	 * 아파트 정보와 아파트 거래 정보를  xml 파일에서 읽어온다.
	 */
	public void loadData() {
		MallSaxParser parser = new MallSaxParser();
		MallInfo = parser.getMallInfo();
		size = parser.getSize();
		search = new ArrayList<MallInfo>(size);
	}
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(MallInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 주택 목록
	 */
	public List<MallInfo> searchAll(MallPageBean  bean){
		search.clear();
		List<MallInfo> finds = new LinkedList<MallInfo>();
		
//		boolean[] type = bean.getSearchType();
//		String city = bean.getCity();
//		String gu = bean.getGu();
//		if(city!=null) {	
//			for (MallInfo deal : search) {
//				if(deal.getCityName().contains(city)) {
//					finds.add(deal);
//				}
//			}
//		}else if(gu != null) {
//			for (MallInfo deal : search) {
//				if(deal.getGuName().contains(gu)) {
//					finds.add(deal);
//				}
//			}
//		}else {
//			finds = search;
//		}
		
		return MallInfo;
	}
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환한다.<br/>
	 * 법정동+아파트명을 이용하여 MallInfo에서 건축연도, 법정코드, 지번, 이미지 정보를 찾아서 MallDeal에 setting한 정보를 반환한다. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public MallInfo search(int no) {
		// complete code #03
		// List<MallDeal> search 로부터 no 에 해당하는 MallDeal 정보를 검색하여 return 하도록 코드를 작성하세요.
		// 해당하는 no 가 없을 경우 null 을 리턴하세요.
		
		for(MallInfo mI : MallInfo) {
			if(mI.getNo() == no) {
				return mI;
			}
		}
		
		return null;
	}
	
	public static void print(List<MallInfo> Malls) {
		for (MallInfo Mall : Malls) {
			System.out.println(Mall);
		}
	}
}
