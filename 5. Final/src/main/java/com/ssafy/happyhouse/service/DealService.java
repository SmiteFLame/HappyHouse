package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.AddressDto;
import com.ssafy.happyhouse.dto.DealDto;
import com.ssafy.happyhouse.dto.LocationDto;
import com.ssafy.happyhouse.dto.PageBean;

public interface DealService {
	public DealDto select(int no);
	public LocationDto getLocation(int no);
	public List<DealDto> selectAll(PageBean bean);

	public List<AddressDto> findnear(String table, String dong);
}
