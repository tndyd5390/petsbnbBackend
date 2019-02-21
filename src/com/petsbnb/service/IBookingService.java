package com.petsbnb.service;

import java.util.HashMap;
import java.util.List;

public interface IBookingService {

	List<HashMap<Object, Object>> getBookingList(HashMap<Object, Object> req) throws Exception;

	HashMap<Object, Object> getBookingDetail(HashMap<Object, Object> req) throws Exception;

	List<HashMap<Object, Object>> getMoreReview(HashMap<Object, Object> req) throws Exception;

	HashMap<Object, Object> insertReview(HashMap<Object, Object> req) throws Exception;
	
}
