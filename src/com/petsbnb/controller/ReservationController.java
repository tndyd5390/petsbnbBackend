package com.petsbnb.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.petsbnb.service.IReservationService;
import com.petsbnb.util.CmmUtil;
import com.petsbnb.util.HttpUtil;

@Controller
public class ReservationController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="ReservationService")
	private IReservationService reservationService;
	
	@RequestMapping(value="/reservation/insertReservationInfo", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> insertReservationInfo(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".insertReservationInfo start!!!");
		
		String imp_uid = CmmUtil.nvl((String)param.get("imp_uid"));
		log.info("imp_uid : " + imp_uid);
		String merchant_uid = CmmUtil.nvl((String)param.get("merchant_uid"));
		log.info("merchant_uid : " + merchant_uid);
		String amount = CmmUtil.nvl((String)param.get("amount"));
		log.info("amount : " + amount);
		String buyerAddr = CmmUtil.nvl((String)param.get("buyer_addr"));
		log.info("buyerAddr : " + buyerAddr);
		String buyerEmail = CmmUtil.nvl((String)param.get("buyer_email"));
		log.info("buyerEmail : " + buyerEmail);
		String buyerName = CmmUtil.nvl((String)param.get("buyer_name"));
		log.info("buyerName : " + buyerName);
		String buyerPostcode = CmmUtil.nvl((String)param.get("buyer_postcode"));
		log.info("buyerPostcode : " + buyerPostcode);
		String buyerTel = CmmUtil.nvl((String)param.get("buyer_tel"));
		log.info("buyerTel : " + buyerTel);
		String petNo = CmmUtil.nvl((String)param.get("petNo"));
		log.info("petNo : " + petNo);
		String checkin = CmmUtil.nvl((String)param.get("checkin"));
		log.info("checkin : " + checkin);
		String checkout = CmmUtil.nvl((String)param.get("checkout"));
		log.info("checkout : " + checkout);
		String serviceProvider = CmmUtil.nvl((String)param.get("serviceProvider"));
		log.info("serviceProvider : " + serviceProvider);
		String serviceReceiver = CmmUtil.nvl((String)param.get("serviceReceiver"));
		log.info("serviceReceiver : " + serviceReceiver);
		String stDate = CmmUtil.nvl((String)param.get("stDate"));
		log.info("stDate : " + stDate);
		String edDate = CmmUtil.nvl((String)param.get("edDate"));
		log.info("edDate : " + edDate);
		
		
		
		log.info(this.getClass() + ".insertReservationInfo end!!!");
		return null;
	}
	
	@RequestMapping(value="/pay/paymentResult")
	public void paymentResult(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".paymentResult start");
		
		String pay_method = CmmUtil.nvl((String)param.get("pay_method"));
		System.out.println("pay_method :" + pay_method);
		String paid_amount= CmmUtil.nvl((String)param.get("paid_amount"));
		System.out.println("paid_amount : " + paid_amount);
		String status= CmmUtil.nvl((String)param.get("status"));
		System.out.println("status : " + status);
		String pg_provider= CmmUtil.nvl((String)param.get("pg_provider"));
		System.out.println("pg_provider : " + pg_provider);
		String pg_tid= CmmUtil.nvl((String)param.get("pg_tid"));
		System.out.println("pg_tid : " + pg_tid);
		String buyer_addr= CmmUtil.nvl((String)param.get("buyer_addr"));
		System.out.println(buyer_addr);
		String buyer_email= CmmUtil.nvl((String)param.get("buyer_email"));
		System.out.println(buyer_email);
		String buyer_name= CmmUtil.nvl((String)param.get("buyer_name"));
		System.out.println(buyer_name);
		String buyer_postcode= CmmUtil.nvl((String)param.get("buyer_postcode"));
		System.out.println(buyer_postcode);
		String buyer_tel= CmmUtil.nvl((String)param.get("buyer_tel"));
		System.out.println(buyer_tel);
		
		
		
		log.info(this.getClass() + "paymentResult end!!!");
	}
	@RequestMapping(value="/pay/networkCheck")
	public @ResponseBody boolean networkCheck() throws Exception{
		log.info(this.getClass() + ".networkCheck start!!!");
		
		
		log.info(this.getClass() + ".networkCheck end!!!");
		return true;
	}
	
	@RequestMapping(value="/payment/getToken", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> getToken(HttpServletRequest req) throws Exception{
		log.info(this.getClass() + ".getToken start!!");
		
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		String token = HttpUtil.getIamportToken();
		
		if (!"".equals(token)) {
			result.put("token", token);
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		log.info(this.getClass() + ".getToken end!!!");
		return result;
	}
}
