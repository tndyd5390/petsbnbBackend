package com.petsbnb.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.persistance.mapper.BookingMapper;
import com.petsbnb.service.IBookingService;
import com.petsbnb.util.DecodeUtil;

@Service("BookingService")
public class BookingService implements IBookingService{
	
	@Resource(name="BookingMapper")
	private BookingMapper bookingMapper;

	@Override
	public List<HashMap<Object, Object>> getBookingList() throws Exception{
		return bookingMapper.getBookingList();
	}

	@Override
	public HashMap<Object, Object> getBookingDetail(String petsitterNo) throws Exception {
		
		HashMap<Object, Object> rslt = new HashMap<Object, Object>();
		
		rslt.put("images", bookingMapper.getBookingDetailImages(petsitterNo));
		rslt.put("details", bookingMapper.getBookingDetail(petsitterNo));
		rslt.put("reviews", DecodeUtil.decodeName(bookingMapper.getBookingDetailReviews(petsitterNo)));
		
		
		return rslt;
	};
	
}
