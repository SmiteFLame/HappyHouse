package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.AddressDto;
import com.ssafy.happyhouse.dto.DealDto;
import com.ssafy.happyhouse.dto.LocationDto;
import com.ssafy.happyhouse.dto.PageBean;

@Mapper
public interface DealDao {
	public LocationDto getLocation(int no);
	public DealDto select(int no);
	public int totalCount(PageBean bean);
	public List<DealDto> selectAll(PageBean bean);
	
	public List<AddressDto> findnear(String table, String dong);
}
