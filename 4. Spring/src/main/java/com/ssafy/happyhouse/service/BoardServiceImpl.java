package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.PageBean;
import com.ssafy.happyhouse.util.PageUtility;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;
	
	@Override
	public BoardDto select(int no) {
		return dao.select(no);
	}

	@Override
	public List<BoardDto> selectAll(PageBean bean) {
		bean.setInterval(15);
		int total = dao.totalCount(bean);
		PageUtility util = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "images/");
		bean.setPageLink(util.getPageBar());
		return dao.selectAll(bean);
	}

}
