package com.petsbnb.service;

import java.util.HashMap;
import java.util.List;

public interface IBookingService {

	List<HashMap<Object, Object>> getBookingList() throws Exception;

	HashMap<Object, Object> getBookingDetail(String petsitterNo) throws Exception;
	
}
