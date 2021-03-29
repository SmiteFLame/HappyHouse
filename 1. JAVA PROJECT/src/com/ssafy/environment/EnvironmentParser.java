package com.ssafy.environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentParser {
	private List<EnvironmentInfo> list;
	
	public void loadData() {
		list = new ArrayList<EnvironmentInfo>();
		File f = new File("res/서울시 종로구 환경 지도점검 내역 현황.csv");
		String line = null;
		if(f.exists()) {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"MS949"));){
				while((line = br.readLine()) != null) {
					EnvironmentInfo info = new EnvironmentInfo();
					String[] content = line.split(",");
					info.setFacName(content[0]);
					info.setLicenseNum(content[1]);
					info.setBusinessCode(content[2]);
					info.setBusinessName(content[3]);
					info.setCheckDate(content[4]);
					info.setCheckAgency(content[5]);
					info.setCheckArea(content[6]);
					info.setRegularCheck(content[7]);
					info.setDisposition(content[8]);
					info.setCheckOption(content[9]);
					info.setResult(content[10]);
					info.setStreetAddress(content[11]);
					info.setAddress(content[12]);
					list.add(info);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("파일 없음");
		}
		
	}
	
	public List<EnvironmentInfo> getEnvironmentInfo(){
		return list;
	}
}
