package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.DealDao;
import com.ssafy.happyhouse.dto.DealDto;
import com.ssafy.happyhouse.dto.LocationDto;
import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.util.PageUtility;

@Service
public class DealServiceImpl implements DealService {

	@Autowired
	DealDao dao;
	
	@Override
	public DealDto select(int no) {
		return dao.select(no);
	}

	@Override
	public List<DealDto> selectAll(PageBean bean) {
		bean.setInterval(15);
		int total = dao.totalCount(bean);
		PageUtility util = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "images/");
		bean.setPageLink(util.getPageBar());
		return dao.selectAll(bean);
	}

	@Override
	public LocationDto getLocation(int no) {
		return dao.getLocation(no);
	}

}
