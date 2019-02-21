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
		String reservationName = CmmUtil.nvl((String)param.get("reservationName"));
		log.info("reservationName : " + reservationName);
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
		rDTO.setReservationName(reservationName);
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
	
	@RequestMapping(value="/reservation/getReservationList", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getReservationList(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getReservationList start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		List<Map<String, Object>> reservationList = reservationService.getReservationList(userNo);
		
		log.info(this.getClass() + ".getReservationList end!!!");
		return reservationList;
	}
	
	@RequestMapping(value="/reservation/getReservationDetail", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getReservationDetail(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getReservationDetail start!!!");
		
		String reservationNo = CmmUtil.nvl((String)param.get("id"));
		log.info("reservationNo : " + reservationNo);
		
		Map<String, Object> reservationDetail = reservationService.getReservationDetatil(reservationNo);
		if(reservationDetail == null) reservationDetail = new HashMap<>();
		
		
		log.info(this.getClass() + ".getReservationDetail end!!!");
		return reservationDetail;
	}
	
	@RequestMapping(value="/reservation/cancelReservation", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelReservation(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".cancelReservation start!!!");
			
		String reservationNo = CmmUtil.nvl((String)param.get("reservationNo"));
		log.info("reservationNo : " + reservationNo);
		String reason = CmmUtil.nvl((String)param.get("reason"));
		log.info("reason : " + reason);
		
		Map<String, Object> reservationDetail = reservationService.updateCancelReservation(reservationNo, reason);
		
		log.info(this.getClass() + ".cancelReservation end!!!");
		return reservationDetail;
	}
	
	@RequestMapping(value="/test/test", method=RequestMethod.POST)
	public void test() throws Exception{
		log.info("test start");
		HttpUtil.cancelIamport("imp_297882245035", "병신");
		log.info("test end");
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
