package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.QnaDao;
import com.ssafy.happyhouse.dto.QnaDto;


@Service
public class QnaServiceImpl implements QnaService {
	
    @Autowired
	private QnaDao QnaDao;

    @Override
	public List<QnaDto> retrieveQna() {
		return QnaDao.selectQna();
	}
    
  	@Override
	public boolean writeQna(QnaDto Qna) {
		return QnaDao.insertQna(Qna) == 1;
	}

	@Override
	public QnaDto detailQna(int no) {
		return QnaDao.selectQnaByNo(no);
	}

	@Override
	@Transactional
	public boolean updateQna(QnaDto Qna) {
		return QnaDao.updateQna(Qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQna(int no) {
		return QnaDao.deleteQna(no) == 1;
	}
}