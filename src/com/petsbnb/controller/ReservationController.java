package com.petsbnb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.petsbnb.dto.ReservationInfoDTO;
import com.petsbnb.service.IReservationService;
import com.petsbnb.util.CmmUtil;
import com.petsbnb.util.HttpUtil;

@Controller
public class ReservationController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="ReservationService")
	private IReservationService reservationService;
	
	/**
	 * 예약을 요청하는 컨트로러이다.
	 * @param param
	 * @return
	 * @throws Exception
	 */
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
		
		/**
		 * petNo는 어플리케이션으로부터 3,4,5 이런식으로 ","로 구분되어서 반려동물의 번호가 넘어온다. 이거는 리스트형식으로 바꿔서 예약 펫리스트 테이블에 다중 insert 한다.
		 */
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
		
		ReservationInfoDTO rDTO = new ReservationInfoDTO();
		rDTO.setImp_uid(imp_uid);
		rDTO.setMerchant_uid(merchant_uid);
		rDTO.setAmount(amount);
		rDTO.setBuyerAddr(buyerAddr);
		rDTO.setBuyerEmail(buyerEmail);
		rDTO.setBuyerName(buyerName);
		rDTO.setBuyerPostcode(buyerPostcode);
		rDTO.setBuyerTel(buyerTel);
		rDTO.setCheckin(checkin);
		rDTO.setCheckout(checkout);
		rDTO.setServiceProvider(serviceProvider);
		rDTO.setServiceReceiver(serviceReceiver);
		rDTO.setStDate(stDate);
		rDTO.setEdDate(edDate);
		//status 컬럼은 예약의 현재 상태를 나타니며 1 = 예약 요청, 2 = 예약 확정, 3 = 예약 반려, 4 = 예약 취소(요청자가 취소 할 수 있음) 5 = 펫시팅 진행, 6 = 펫시팅 완료임
		//여기서는 최초 펫시팅 요청이니까 하드코딩으로 1을 박아버림ㄴ
		rDTO.setStatus("1");
		rDTO.setDepositRefund("0");
		
		String[] petNoArr = petNo.split(",");
		List<String> petNoList = new ArrayList<String>();
		for(int i = 0; i< petNoArr.length; i++){
			petNoList.add(petNoArr[i]);
		}
		
		boolean result = reservationService.insertReservationInfo(rDTO, petNoList);
		
		String token = reservationService.getServiceProviderToken(serviceProvider);
		HttpUtil.sendFcm("새로운 펫시팅 요청", "새로운 펫시팅 요청이 있습니다. 확인해주세요.", token);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		
		log.info(this.getClass() + ".insertReservationInfo end!!!");
		return resultMap;
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
