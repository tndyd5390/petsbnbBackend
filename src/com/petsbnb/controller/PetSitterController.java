package com.petsbnb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.service.IPetSitterServcie;
import com.petsbnb.util.CmmUtil;

@Controller
public class PetSitterController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="PetSitterService")
	private IPetSitterServcie petSitterService;
	
	@RequestMapping(value="/petSitter/getPetSitterInfo", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> getPetSitterInfo(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getPetSitterInfo start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		PetSitterDTO pDTO = petSitterService.getPetSitterInfo(userNo);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("petSitterInfo", pDTO);
		
		log.info(this.getClass() + ".getPetSitterInfo end!!!");
		return resultMap;
	}
	
	
	@RequestMapping(value="/petSitter/petSitterRegProc", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> petSitterRegProc(MultipartHttpServletRequest req) throws Exception{
		log.info(this.getClass() + ".petSitterRegProc start!!!");
		String userNo = CmmUtil.nvl(req.getParameter("userNo"));
		log.info("userNo : " + userNo);
		String petSitterName = CmmUtil.nvl(req.getParameter("petSitterName"));
		log.info("petSitterName : " + petSitterName);
		String petSitterIntroduceOneLine = CmmUtil.nvl(req.getParameter("petSitterIntroduceOneLine"));
		log.info("petSitterIntroduceOneLine : " + petSitterIntroduceOneLine);
		String petSitterEnv = CmmUtil.nvl(req.getParameter("petSitterEnv"));
		log.info("petSitterEnv : " + petSitterEnv);
		String petSitterHasPet = CmmUtil.nvl(req.getParameter("petSitterHasPet"));
		log.info("petSitterHasPet : " + petSitterHasPet);
		String longTermAvailable = CmmUtil.nvl(req.getParameter("longTermAvailable"));
		log.info("longTermAvailable : " + longTermAvailable);
		String walkAvailable = CmmUtil.nvl(req.getParameter("walkAvailable"));
		log.info("walkAvailable : " + walkAvailable);
		String bathAvailable = CmmUtil.nvl(req.getParameter("bathAvailable"));
		log.info("bathAvailable : " + bathAvailable);
		String firstaidAvailable = CmmUtil.nvl(req.getParameter("firstaidAvailable"));
		log.info("firstaidAvailable : " + firstaidAvailable);
		String haircareAvailable = CmmUtil.nvl(req.getParameter("haircareAvailable"));
		log.info("haircareAvailable : " + haircareAvailable);
		String markingImpossible = CmmUtil.nvl(req.getParameter("markingImpossible"));
		log.info("markingImpossible : " + markingImpossible);
		String bowelImpossible = CmmUtil.nvl(req.getParameter("bowelImpossible"));
		log.info("bowelImpossible : " + bowelImpossible);
		String attackImpossible = CmmUtil.nvl(req.getParameter("attackImpossible"));
		log.info("attackImpossible : " + attackImpossible);
		String separationImpossible = CmmUtil.nvl(req.getParameter("separationImpossible"));
		log.info("separationImpossible : " + separationImpossible);
		String biteImpossible = CmmUtil.nvl(req.getParameter("biteImpossible"));
		log.info("biteImpossible : " + biteImpossible);
		String smallPetNightPrice = CmmUtil.nvl(req.getParameter("smallPetNightPrice"));
		log.info("smallPetNightPrice : " + smallPetNightPrice);
		String smallPetDayPrice = CmmUtil.nvl(req.getParameter("smallPetDayPrice"));
		log.info("smallPetDayPrice : " + smallPetDayPrice);
		String middlePetDayPrice = CmmUtil.nvl(req.getParameter("middlePetDayPrice"));
		log.info("middlePetDayPrice : " + middlePetDayPrice);
		String middlePetNightPrice = CmmUtil.nvl(req.getParameter("middlePetNightPrice"));
		log.info("middlePetNightPrice : " + middlePetNightPrice);
		String bigPetDayPrice = CmmUtil.nvl(req.getParameter("bigPetDayPrice"));
		log.info("bigPetDayPrice : " + bigPetDayPrice);
		String bigPetNightPrice = CmmUtil.nvl(req.getParameter("bigPetNightPrice"));
		log.info("bigPetNightPrice : " + bigPetNightPrice);
		String refundAccountName = CmmUtil.nvl(req.getParameter("refundAccountName"));
		log.info("refundAccountName : " + refundAccountName);
		String refundBank = CmmUtil.nvl(req.getParameter("refundBank"));
		log.info("refundBank : " + refundBank);
		String refundAccountNumber = CmmUtil.nvl(req.getParameter("refundAccountNumber"));
		log.info("refundAccountNumber : " + refundAccountNumber);
		String necessaryItem = CmmUtil.nvl(req.getParameter("necessaryItem"));
		log.info("necessaryItem : " + necessaryItem);
		String petSitterIntroduce = CmmUtil.nvl(req.getParameter("petSitterIntroduce"));
		log.info("petSitterNapetSitterIntroduceme : " + petSitterIntroduce);
		
		
		
		log.info(this.getClass() + ".petSitterRegProc end!!!");
		return null;
	}
	
}
