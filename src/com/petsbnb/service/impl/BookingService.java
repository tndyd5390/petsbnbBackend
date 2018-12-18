package com.petsbnb.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.persistance.mapper.BookingMapper;
import com.petsbnb.service.IBookingService;

@Service("BookingService")
public class BookingService implements IBookingService{
	
	@Resource(name="BookingMapper")
	private BookingMapper bookingMapper;

	@Override
	public List<HashMap<Object, Object>> getBookingList() {
		return bookingMapper.getBookingList();
	};
	
}
