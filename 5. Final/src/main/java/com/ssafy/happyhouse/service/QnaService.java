package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.QnaDto;


public interface QnaService {
	public List<QnaDto> retrieveQna();
	public QnaDto detailQna(int no);
	public boolean writeQna(QnaDto Qna);
	public boolean updateQna(QnaDto Qna);
	public boolean deleteQna(int no);
}
