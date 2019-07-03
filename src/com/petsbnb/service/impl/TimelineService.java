package com.petsbnb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.persistance.mapper.TimelineMapper;
import com.petsbnb.service.ITimelineService;
import com.petsbnb.util.AES256Util;


@Service("TimelineService")
public class TimelineService implements ITimelineService{

	@Resource(name="TimelineMapper")
	private TimelineMapper timelineMapper;

	@Override
	public List<Map<Object, Object>> getTimeList(String reservationNo) throws Exception {
		List<Map<Object, Object>> timelineList = timelineMapper.getTimelineList(reservationNo);
		for(Map<Object, Object> timeline : timelineList){
			timeline.put("REGISTER_NAME", AES256Util.strDecode((String)timeline.get("REGISTER_NAME")));
		}
		return timelineList;
	}

	@Override
	public boolean insertTimeline(Map<String, String> param) throws Exception {
		int insertTimelineInfo = timelineMapper.insertTimelineInfo(param);
		int insertTimelineFileInfo = timelineMapper.insertTimelineFileInfo(param);
		return insertTimelineInfo > 0 && insertTimelineFileInfo > 0;
	}
	
}
