package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.CommunityDao;
import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.dto.CommunityDto;
import com.ssafy.happyhouse.dto.PageBean;


@Service
public class CommunityServiceImpl implements CommunityService {
	
    @Autowired
	private CommunityDao CommunityDao;

    @Override
	public List<CommunityDto> retrieveCommunity(PageBean pageBean) {
		return CommunityDao.selectCommunity(pageBean);
	}
    
  	@Override
	public boolean writeCommunity(CommunityDto Community) {
		return CommunityDao.insertCommunity(Community);
	}

	@Override
	public CommunityDto detailCommunity(int no) {
		return CommunityDao.selectCommunityByNo(no);
	}

	@Override
	@Transactional
	public boolean updateCommunity(CommunityDto Community) {
		return CommunityDao.updateCommunity(Community);
	}

	@Override
	@Transactional
	public boolean deleteCommunity(int no) {
		return CommunityDao.deleteCommunity(no);
	}

	@Override
	public List<CommentDto> selectComments(int cno) {
		return CommunityDao.selectComments(cno);
	}

	@Override
	public boolean insertComment(CommentDto comment) {
		return CommunityDao.insertComment(comment);
	}

	@Override
	public boolean deleteComment(int no) {
		return CommunityDao.deleteComment(no);
	}
}