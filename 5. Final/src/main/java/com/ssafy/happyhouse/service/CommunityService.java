package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.dto.CommunityDto;
import com.ssafy.happyhouse.dto.PageBean;


public interface CommunityService {
	public List<CommunityDto> retrieveCommunity(PageBean pageBean);
	public CommunityDto detailCommunity(int no);
	public boolean writeCommunity(CommunityDto Community);
	public boolean updateCommunity(CommunityDto Community);
	public boolean deleteCommunity(int no);
	
	public List<CommentDto> selectComments(int cno);
	public boolean insertComment(CommentDto comment);
	public boolean deleteComment(int no);
}
