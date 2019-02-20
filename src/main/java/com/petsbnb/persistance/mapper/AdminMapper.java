package com.petsbnb.persistance.mapper;

import java.util.List;
import java.util.Map;

import com.petsbnb.config.Mapper;

@Mapper("AdminMapper")
public interface AdminMapper {

	List<Map<String, Object>> getPointinfo()throws Exception;

	int updateStatus(String pointNo)throws Exception;

	List<Map<String, Object>> getPointPaymentinfo()throws Exception;

	int updateStatus2(String pointNo)throws Exception;
	
}
