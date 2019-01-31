package com.petsbnb.persistance.mapper;

import java.util.HashMap;
import java.util.List;

import com.petsbnb.config.Mapper;

@Mapper("BookingMapper")
public interface BookingMapper {

	List<HashMap<Object, Object>> getBookingList(HashMap<Object, Object> req) throws Exception;

	List<HashMap<Object, Object>> getBookingDetailImages(String petsitterNo) throws Exception;

	HashMap<Object, Object> getBookingDetail(String petsitterNo) throws Exception;

	List<HashMap<Object, Object>> getBookingDetailReviews(HashMap<Object, Object> req) throws Exception;

	HashMap<Object, Object> getPetsitterUserImage(String petsitterNo) throws Exception;
}
