package com.petsbnb.persistance.mapper;

import java.util.HashMap;
import java.util.List;

import com.petsbnb.config.Mapper;

@Mapper("BookingMapper")
public interface BookingMapper {

	List<HashMap<Object, Object>> getBookingList();
	
}
