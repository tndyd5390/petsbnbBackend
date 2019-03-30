package com.petsbnb.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petsbnb.service.ITimelineService;
import com.petsbnb.util.CmmUtil;

@Controller
public class TimelineController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="TimelineService")
	private ITimelineService timelineService;
	
	
	@RequestMapping(value="/timeline/getTimelineList", method=RequestMethod.POST)
	public @ResponseBody List<String> getTimelineList(@RequestBody Map<Object, Object> param) throws Exception{
		
		log.info(this.getClass() + ".getTimelineList start!!!");
		
		String reservationNo = CmmUtil.nvl((String)param.get("reservationNo"));
		log.info("reservationNo : " + reservationNo);
		
		
		
		
		log.info(this.getClass() + ".getTimelineList end!!!");
		return null;
	}
}
