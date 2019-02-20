package com.petsbnb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.persistance.mapper.AdminMapper;
import com.petsbnb.service.IAdminService;

@Service("AdminService")
public class AdminService implements IAdminService{
	
	@Resource(name="AdminMapper")
	private AdminMapper adminMapper;

	@Override
	public List<Map<String, Object>> getPointinfo() throws Exception {
		return adminMapper.getPointinfo();
	}

	@Override
	public int updateStatus(String pointNo) throws Exception {
		return adminMapper.updateStatus(pointNo);
	}

	@Override
	public List<Map<String, Object>> getPointPaymentinfo() throws Exception {
		return adminMapper.getPointPaymentinfo();
	}

	@Override
	public int updateStatus2(String pointNo) throws Exception {
		return adminMapper.updateStatus2(pointNo);
	}
	
	
}
