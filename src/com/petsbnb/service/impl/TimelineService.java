package com.petsbnb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.persistance.mapper.TimelineMapper;
import com.petsbnb.service.ITimelineService;


@Service("TimelineService")
public class TimelineService implements ITimelineService{

	@Resource(name="TimelineMapper")
	private TimelineMapper timelineMapper;

	@Override
	public List<Map<Object, Object>> getTimeList(String reservationNo) throws Exception {
		
		
		
		return null;
	}
	
}
