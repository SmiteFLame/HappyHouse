package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.PageBean;

public interface BoardService {
	public BoardDto select(int no);
	public List<BoardDto> selectAll(PageBean bean);
}
