package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.dto.CommunityDto;
import com.ssafy.happyhouse.dto.PageBean;

@Mapper
public interface CommunityDao {
	public List<CommunityDto> selectCommunity(PageBean pagebean);
	public CommunityDto selectCommunityByNo(int no);
	public boolean insertCommunity(CommunityDto Community);
	public boolean updateCommunity(CommunityDto Community);
	public boolean deleteCommunity(int no);

	public List<CommentDto> selectComments(int cno);
	public boolean insertComment(CommentDto comment);
	public boolean deleteComment(int no);
	
}