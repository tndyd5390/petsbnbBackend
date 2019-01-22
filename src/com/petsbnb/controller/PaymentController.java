package com.petsbnb.controller;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petsbnb.util.CmmUtil;

@Controller
public class PaymentController {

	private Logger log = Logger.getLogger(this.getClass());
	
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
}
