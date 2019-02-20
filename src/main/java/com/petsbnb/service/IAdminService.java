package com.petsbnb.service;

import java.util.List;
import java.util.Map;

public interface IAdminService {

	List<Map<String, Object>> getPointinfo() throws Exception;

	int updateStatus(String pointNo)throws Exception;

	List<Map<String, Object>> getPointPaymentinfo()throws Exception;

	int updateStatus2(String pointNo)throws Exception;

}
