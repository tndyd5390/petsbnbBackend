package com.petsbnb.service;

import java.util.List;
import java.util.Map;

public interface ITimelineService {
	
	public List<Map<Object, Object>> getTimeList(String reservationNo) throws Exception;
	public boolean insertTimeline(Map<String, String> param) throws Exception;
}
