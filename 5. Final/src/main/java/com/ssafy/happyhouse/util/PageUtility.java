package com.ssafy.happyhouse.util;

import java.util.Arrays;

/**
 * JSP에서 데이터를 page navigation 형식으로 구성하기 위해 사용되는 클래스이다.
 */
public class PageUtility {
	int firstpagecount = 0;
	int lastpagecount = 0;

	int nextpagecount = 0; // 다음 페이지
	int beforepagecount = 0; // 이전 페이지
	int currentpagecount = 0; // 현재 페이지

	int beforetenpage = 0; // 이전 페이지
	int nexttenpage = 0; // 다음 페이지

	int totalrowcount = 0; // 총 row 개수
	int totalpagecount = 0; // 총 페이지 수
	int displayrowcount = 0; // 한 페이지당 보여줄 개수
	int pagePercount = 5; // 페이지 링크 거는 개수 << < 1 2 3 4 5 > >>
	String imagepath;
	String search;

	/**
	 * 현재페이지와 경로 한번에 보여줄 열의 갯수를 세팅하는 생성자
	 * 
	 * @param displayrowcount  한 페이지에 보여줄 게시글 수
	 * @param totalrowcount    조회해온 데이터의 전체 row수
	 * @param currentpagecount 현재 페이지
	 * @param imagepath        이미지 경로
	 * @exception java.lang.Exception
	 */
	public PageUtility(int displayrowcount, int totalrowcount, int currentpagecount, String imagepath) {
		this.displayrowcount = displayrowcount;
		this.totalrowcount = totalrowcount;
		this.currentpagecount = currentpagecount;
		this.imagepath = imagepath;
		this.totalpagecount = totalrowcount / displayrowcount;
		if (totalrowcount % displayrowcount != 0) {
			this.totalpagecount++;
		}

	}

	public String getCurrentPageCount() {
		return String.valueOf(currentpagecount);
	}

	/**
	 * 목록에 출력할 page link를 구성한다.
	 * 
	 * @return 구성한 page 링크를 리턴
	 */
	public int[] getPageBar() {

		StringBuffer sb = new StringBuffer();
		
		int[] pageList;
		if(totalpagecount > 5) {
			int startNo = currentpagecount - 2;
			int endNo = currentpagecount + 2;
			if(startNo < 2) {
				startNo = 1;
			} else if(currentpagecount + 2 > totalpagecount) {
				startNo -= (currentpagecount - 2);
			}
			pageList = new int[5];
			for(int i = 0; i < 5; i++) {
				pageList[i] = startNo + i;
			}
		} else {
			pageList = new int[totalpagecount];
			for(int i = 0; i < totalpagecount; i++) {
				pageList[i] = i + 1;
			}
		}
		
		return pageList;
	}

	public String getTotalPageCount() {
		return String.valueOf(totalpagecount);
	}
}