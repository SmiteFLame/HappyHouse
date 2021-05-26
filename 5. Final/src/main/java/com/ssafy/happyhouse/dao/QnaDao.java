package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.QnaDto;

@Mapper
public interface QnaDao {
	public List<QnaDto> selectQna();
	public QnaDto selectQnaByNo(int no);
	public int insertQna(QnaDto Qna);
	public int updateQna(QnaDto Qna);
	public int deleteQna(int no);
}