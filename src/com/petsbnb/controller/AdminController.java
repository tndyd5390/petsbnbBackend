package com.petsbnb.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petsbnb.service.IAdminService;
import com.petsbnb.service.impl.AdminService;
import com.petsbnb.util.AES256Util;
import com.petsbnb.util.JSON;
@Controller
public class AdminController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="AdminService")
	private IAdminService adminService;
	
	@RequestMapping(value="admin")
	public String admin() {
		log.info(this.getClass()+" enter!");
		
		return "admin/main";
	}
	
	@RequestMapping(value="pointRequest")
	public String pointRequest(Model model)throws Exception {
		log.info(this.getClass().getName() + " start!!");
		
		List<Map<String, Object>> info = adminService.getPointinfo();
		
		//Decode
		for(Map<String, Object> m : info) {
			m.put("REFUND_ACCOUNT_NAME", AES256Util.strDecode((String)m.get("REFUND_ACCOUNT_NAME")));
			m.put("REFUND_BANK", AES256Util.strDecode((String)m.get("REFUND_BANK")));
			m.put("REFUND_ACCOUNT_NUMBER", AES256Util.strDecode((String)m.get("REFUND_ACCOUNT_NUMBER")));
		}
		
		JSONArray jsonArray = new JSONArray();
        jsonArray = JSON.getJsonArrayFromList(info);
		
        model.addAttribute("result",jsonArray);
        
		return "admin/pointRequest";
	}
	
	@RequestMapping(value="updateStatus")
	public @ResponseBody int  updateStatus(
			@RequestParam(value="point_info_no")String pointNo,
			@RequestParam(value="status")String status
			)throws Exception {
		log.info(this.getClass().getName() + " start!");
		log.info(status);
		
		int result = 0;
		
		if(status.equals("2")) {
			result = adminService.updateStatus(pointNo);
		}else if(status.equals("3")) {
			result = adminService.updateStatus2(pointNo);
		}
		
		
		log.info(result);
		
		return result;
	}
	
	@RequestMapping(value="pointPayment")
	public String pointPayment(Model model)throws Exception {
		log.info(this.getClass().getName() + " start!!");
		
		List<Map<String, Object>> info = adminService.getPointPaymentinfo();
		
		//Decode
		for(Map<String, Object> m : info) {
			m.put("REFUND_ACCOUNT_NAME", AES256Util.strDecode((String)m.get("REFUND_ACCOUNT_NAME")));
			m.put("REFUND_BANK", AES256Util.strDecode((String)m.get("REFUND_BANK")));
			m.put("REFUND_ACCOUNT_NUMBER", AES256Util.strDecode((String)m.get("REFUND_ACCOUNT_NUMBER")));
		}
		
		JSONArray jsonArray = new JSONArray();
        jsonArray = JSON.getJsonArrayFromList(info);
		
        model.addAttribute("result",jsonArray);
        
		return "admin/pointPayment";
	}
	

}
