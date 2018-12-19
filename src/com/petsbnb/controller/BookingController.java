package com.petsbnb.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petsbnb.service.IBookingService;

@Controller
public class BookingController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="BookingService")
	private IBookingService bookingService;
	
	@RequestMapping("/booking/getBookingList")
	public @ResponseBody List<HashMap<Object, Object>> getBookingList() throws Exception{
		log.info(this.getClass().getName() + "start!!");

		
		List<HashMap<Object,Object>> rsltList = bookingService.getBookingList();
		
		log.info(this.getClass().getName() + "end!!");
		return rsltList;
	}
	
	@RequestMapping("/booking/getBookingDetail")
	public @ResponseBody HashMap<Object, Object> getBookingDetail(@RequestBody HashMap<Object, Object> req) throws Exception{
		log.info(this.getClass().getName() + "start!!");
		
		String petsitterNo = req.get("petsitterNo").toString();
		System.out.println(petsitterNo);
		
		HashMap<Object, Object> rslt = bookingService.getBookingDetail(petsitterNo);
		
		log.info(this.getClass().getName() + "end!!");
		return rslt;
	}
}
