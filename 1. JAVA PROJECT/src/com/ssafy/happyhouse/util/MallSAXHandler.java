package com.ssafy.happyhouse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.Mall.dto.MallInfo;


/**
 *  AptDealHistory.xml 파일에서 아파트 거래 정보를 읽어 파싱하는 핸들러 클래스 
 */
public class MallSAXHandler extends DefaultHandler {
	/**
	 * 아파트 거래 정보를 식별하기 위한 번호로 차후 DB에서는 primary key로 대체하지만 
	 * 현재 버전에서는 0번부터 순차 부여한다. 
	 */
	private int no;
	/**아파트 거래 정보를 담는다 */
	private List<MallInfo> malls;
	/**파상힌 아파트 거래 정보*/
	private MallInfo mall;
	/**태그 바디 정보를 임시로 저장*/
	private String temp;
	public MallSAXHandler(){
		malls = new LinkedList<MallInfo>();
		loadData();
		
	}
	public void characters(char[]ch, int start, int length){
		temp = new String(ch, start, length);
	}
	public List<MallInfo> getMallInfo() {
		return malls;
	}
	
	private void loadData() {

		File f = new File("res/CommercialArea.csv");
		if(f.exists()) {	// 파일이 존재하면 파일에서 데이터 읽기
			System.out.println("파읽 읽음");
			// 파일에서 읽어오기 위해 FileInputStream을 생성 후 저장된 도서리스트 객체를 읽어오기 위해 ObjectInputStream을 생성한다.
			try(BufferedReader io = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"))){	
				String S = io.readLine();
				io.readLine();
				while((S = io.readLine()) != null) {
					String[] data = new String[22];
					String temp = "";
					boolean check = true;
					for(int i = 0, idx = 0, end = S.length(); i < end; i++) {
						if(S.charAt(i) == '"') check = !check;
						if(check && S.charAt(i) == ',') {
							if(temp.length() > 0 && temp.charAt(0) == '"') {
								temp = temp.substring(1, temp.length() - 2);
							}
							data[idx++] = temp;
							temp = "";
						} else {
							temp += S.charAt(i);
						}
					}
//					System.out.println(Arrays.toString(data));
					
					mall = new MallInfo(Integer.parseInt(data[0]));
					mall.setShopName(data[1]);
					mall.setLocalName(data[2]);
					mall.setCode3(data[8]);
					mall.setCode4(data[10]);
					mall.setCityName(data[12]);
					mall.setGuName(data[14]);
					mall.setDongName(data[16]);
					mall.setJibuaddress(data[17]);
					mall.setDoroaddress(data[18]);
					malls.add(mall);
				}
			} catch (Exception e) {
				System.out.println("[SYSTEM]파일 읽기에 실패하였습니다.");
				e.printStackTrace();
			} 
		}else {
			System.out.println("파일 없음");
		}
	}
}





