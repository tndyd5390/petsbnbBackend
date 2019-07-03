package com.petsbnb.persistance.mapper;

import java.util.List;
import java.util.Map;

import com.petsbnb.config.Mapper;

@Mapper("TimelineMapper")
public interface TimelineMapper {
	public List<Map<Object, Object>> getTimelineList(String reservationNo) throws Exception;
	public int insertTimelineInfo(Map<String, String> param) throws Exception;
	public int insertTimelineFileInfo(Map<String, String> param) throws Exception;
	
}
