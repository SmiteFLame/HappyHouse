package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.PageBean;

@Mapper
public interface BoardDao {
	public BoardDto select(int no);
	public int totalCount(PageBean bean);
	public List<BoardDto> selectAll(PageBean bean);
	

	public boolean insertBoard(BoardDto board);
	public boolean updateBoard(BoardDto board);
	public boolean deleteBoard(int no);
}
