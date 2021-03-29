package com.ssafy.environment;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentDao {
	private List<EnvironmentInfo> list;
	
	public EnvironmentDao() {
		loadData();
	}
	
	public void loadData() {
		EnvironmentParser parser = new EnvironmentParser();
		parser.loadData();
		list = parser.getEnvironmentInfo();
//		for(EnvironmentInfo info : list)
//			System.out.println(info.toString());
	}
	
	// 동을 클릭하면 해당 동과 관련된 환경 지도점검을 return
	public List<EnvironmentInfo> searchAll(String dong){
		List<EnvironmentInfo> temp = new ArrayList<EnvironmentInfo>();
		for(EnvironmentInfo info : list) {
			if(info.getAddress().contains(dong)) {
				temp.add(info);
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {
		EnvironmentDao dao = new EnvironmentDao();
		List<EnvironmentInfo> temp = dao.searchAll("익선동");
		for(EnvironmentInfo info : temp)
			System.out.println(info);;
	}
}
