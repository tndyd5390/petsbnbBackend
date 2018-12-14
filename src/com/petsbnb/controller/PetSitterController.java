package com.petsbnb.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petsbnb.dto.PetFileDTO;
import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.dto.PetSitterFileDTO;
import com.petsbnb.service.IPetSitterServcie;
import com.petsbnb.util.AES256Util;
import com.petsbnb.util.CmmUtil;

@Controller
public class PetSitterController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="PetSitterService")
	private IPetSitterServcie petSitterService;
	
	String petSitterImageFilePath = "C:\\Users\\DATA16\\git\\petsbnbBackend\\WebContent\\petSitterImageFile\\";
	
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
		refundAccountName = AES256Util.strEncode(refundAccountName);
		log.info("refundAccountName : " + refundAccountName);
		
		String refundBank = CmmUtil.nvl(req.getParameter("refundBank"));
		refundBank = AES256Util.strEncode(refundBank);
		log.info("refundBank : " + refundBank);
		
		String refundAccountNumber = CmmUtil.nvl(req.getParameter("refundAccountNumber"));
		refundAccountNumber = AES256Util.strEncode(refundAccountNumber);
		log.info("refundAccountNumber : " + refundAccountNumber);
		
		String necessaryItem = CmmUtil.nvl(req.getParameter("necessaryItem"));
		log.info("necessaryItem : " + necessaryItem);
		
		String petSitterIntroduce = CmmUtil.nvl(req.getParameter("petSitterIntroduce"));
		log.info("petSitterNapetSitterIntroduceme : " + petSitterIntroduce);
		
		
		PetSitterDTO psDTO = new PetSitterDTO();
		psDTO.setUserNo(userNo);
		psDTO.setPetSitterName(petSitterName);
		psDTO.setPetSitterIntroduceOneLine(petSitterIntroduceOneLine);
		psDTO.setPetSitterEnv(petSitterEnv);
		psDTO.setPetSitterHasPet(petSitterHasPet);
		psDTO.setLongTermAvailable(longTermAvailable);
		psDTO.setWalkAvailable(walkAvailable);
		psDTO.setBathAvailable(bathAvailable);
		psDTO.setFirstaidAvailable(firstaidAvailable);
		psDTO.setHaircareAvailable(haircareAvailable);
		psDTO.setMarkingImpossible(markingImpossible);
		psDTO.setBowelImpossible(bowelImpossible);
		psDTO.setAttackImpossible(attackImpossible);
		psDTO.setSeparationImpossible(separationImpossible);
		psDTO.setBiteImpossible(biteImpossible);
		psDTO.setSmallPetNightPrice(smallPetNightPrice);
		psDTO.setSmallPetDayPrice(smallPetDayPrice);
		psDTO.setMiddlePetNightPrice(middlePetNightPrice);
		psDTO.setMiddlePetDayPrice(middlePetDayPrice);
		psDTO.setBigPetNightPrice(bigPetNightPrice);
		psDTO.setBigPetDayPrice(bigPetDayPrice);
		psDTO.setRefundAccountName(refundAccountName);
		psDTO.setRefundBank(refundBank);
		psDTO.setRefundAccountNumber(refundAccountNumber);
		psDTO.setNecessaryItem(necessaryItem);
		psDTO.setPetSitterIntroduce(petSitterIntroduce);
		psDTO.setRegNo(userNo);
		
		
		List<PetSitterFileDTO> psList = new ArrayList<>();
		
		Iterator<String> fileNames =  req.getFileNames();
		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile file = req.getFile(fileName);
			
			String reFileName = "";
			String fileOrgName = file.getOriginalFilename();
			String fileType = file.getContentType();
			log.info(this.getClass() + ".file.petSitterImageFilePath() : " + file.getOriginalFilename());
			
			String extended = "." + fileType.substring(fileType.indexOf("/") + 1, fileType.length());
			String now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
			
			reFileName = petSitterImageFilePath + now + extended;
			
			File newFile = new File(reFileName);
			file.transferTo(newFile);
	
			PetSitterFileDTO pfDTO = new PetSitterFileDTO();
			pfDTO.setPetSitterFileOrgName(fileOrgName);
			pfDTO.setPetSitterFileName(now + extended);
			pfDTO.setPetSitterFilePath(petSitterImageFilePath);
			pfDTO.setRegNo(userNo);
			pfDTO.setUserNo(userNo);
			
			psList.add(pfDTO);
		}
		
		Map<String, Object> psMap = new HashMap<>();
		psMap.put("petSitterImages", psList);
		
		boolean result = petSitterService.insertPetSitterInfo(psMap, psDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		
		log.info(this.getClass() + ".petSitterRegProc end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/petSitter/getPetSitterInfoWithImage", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> getPetSitterInfoWithImage(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getPetSitterInfoWithImage start!!!");
		
		String petSitterNo = CmmUtil.nvl((String)param.get("petSitterNo"));
		log.info("petSitterNo : " + petSitterNo);
		
		Map<Object, Object> result = petSitterService.getPetSitterInfoWithImage(petSitterNo);
		log.info(this.getClass() + ".getPetSitterInfoWithImage end!!!");
		return result;
	}
	
	@RequestMapping(value="/petSitter/petSitterImageUpload", method=RequestMethod.POST)
	public @ResponseBody List<PetSitterFileDTO> petSitterImageUpload(MultipartHttpServletRequest req) throws Exception{
		log.info(this.getClass() + ".petSitterImageUpload start!!!");
		
		String userNo = CmmUtil.nvl(req.getParameter("userNo"));
		log.info("userNo : " + userNo);
		String petSitterNo = CmmUtil.nvl(req.getParameter("petSitterNo"));
		log.info("petSitterNo : " + petSitterNo);
		
		MultipartFile file = req.getFile("petSitterImage");
		
		String reFileName = "";
		String fileOrgName = file.getOriginalFilename();
		String fileType = file.getContentType();
		log.info(this.getClass() + ".file.petSitterImageFilePath() : " + file.getOriginalFilename());
		
		String extended = "." + fileType.substring(fileType.indexOf("/") + 1, fileType.length());
		String now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
		
		reFileName = petSitterImageFilePath + now + extended;
		
		File newFile = new File(reFileName);
		file.transferTo(newFile);

		PetSitterFileDTO pfDTO = new PetSitterFileDTO();
		pfDTO.setPetSitterFileOrgName(fileOrgName);
		pfDTO.setPetSitterNo(petSitterNo);
		pfDTO.setPetSitterFileName(now + extended);
		pfDTO.setPetSitterFilePath(petSitterImageFilePath);
		pfDTO.setRegNo(userNo);
		pfDTO.setUserNo(userNo);
		
		List<PetSitterFileDTO> pList = petSitterService.insertAndGetPetSitterImage(pfDTO);
		
		log.info(this.getClass() + ".petSitterImageUpload end!!!");
		return pList;
	}
	
	@RequestMapping(value="/petSitter/deletePetSitterImage", method=RequestMethod.POST)
	public @ResponseBody List<PetSitterFileDTO> deletePetSitterImage(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".deletePetSitterImage start!!!");
		
		String petSitterFileNo = CmmUtil.nvl((String)param.get("petSitterFileNo"));
		log.info("petSitterFileNo : " + petSitterFileNo);
		String petSitterNo = CmmUtil.nvl((String)param.get("petSitterNo"));
		log.info("petSitterNo : " + petSitterNo);
		
		List<PetSitterFileDTO> pList = petSitterService.deleteAndGetPetSitterImage(petSitterNo, petSitterFileNo);
		
		log.info(this.getClass() + ".deletePetSitterImage end!!!");
		return pList;
	}
	
	@RequestMapping(value="/petSitter/updatePetSitterProc", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> updatePetSitterProc(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".updatePetSitterProc start!!!");

		String petSitterNo = CmmUtil.nvl((String)param.get("petSitterNo")); 
		log.info("petSitterNo : " + petSitterNo);
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		String petSitterName = CmmUtil.nvl((String)param.get("petSitterName"));
		log.info("petSitterName : " + petSitterName);
		
		String petSitterIntroduceOneLine = CmmUtil.nvl((String)param.get("petSitterIntroduceOneLine"));
		log.info("petSitterIntroduceOneLine : " + petSitterIntroduceOneLine);
		
		String petSitterEnv = CmmUtil.nvl((String)param.get("petSitterEnv"));
		log.info("petSitterEnv : " + petSitterEnv);
		
		String petSitterHasPet = CmmUtil.nvl((String)param.get("petSitterHasPet"));
		log.info("petSitterHasPet : " + petSitterHasPet);
		
		String longTermAvailable = CmmUtil.nvl((String)param.get("longTermAvailable"));
		log.info("longTermAvailable : " + longTermAvailable);
		
		String walkAvailable = CmmUtil.nvl((String)param.get("walkAvailable"));
		log.info("walkAvailable : " + walkAvailable);
		
		String bathAvailable = CmmUtil.nvl((String)param.get("bathAvailable"));
		log.info("bathAvailable : " + bathAvailable);
		
		String firstaidAvailable = CmmUtil.nvl((String)param.get("firstaidAvailable"));
		log.info("firstaidAvailable : " + firstaidAvailable);
		
		String haircareAvailable = CmmUtil.nvl((String)param.get("haircareAvailable"));
		log.info("haircareAvailable : " + haircareAvailable);
		
		String markingImpossible = CmmUtil.nvl((String)param.get("markingImpossible"));
		log.info("markingImpossible : " + markingImpossible);
		
		String bowelImpossible = CmmUtil.nvl((String)param.get("bowelImpossible"));
		log.info("bowelImpossible : " + bowelImpossible);
		
		String attackImpossible = CmmUtil.nvl((String)param.get("attackImpossible"));
		log.info("attackImpossible : " + attackImpossible);
		
		String separationImpossible = CmmUtil.nvl((String)param.get("separationImpossible"));
		log.info("separationImpossible : " + separationImpossible);
		
		String biteImpossible = CmmUtil.nvl((String)param.get("biteImpossible"));
		log.info("biteImpossible : " + biteImpossible);
		
		String smallPetNightPrice = CmmUtil.nvl((String)param.get("smallPetNightPrice"));
		log.info("smallPetNightPrice : " + smallPetNightPrice);
		
		String smallPetDayPrice = CmmUtil.nvl((String)param.get("smallPetDayPrice"));
		log.info("smallPetDayPrice : " + smallPetDayPrice);
		
		String middlePetDayPrice = CmmUtil.nvl((String)param.get("middlePetDayPrice"));
		log.info("middlePetDayPrice : " + middlePetDayPrice);
		
		String middlePetNightPrice = CmmUtil.nvl((String)param.get("middlePetNightPrice"));
		log.info("middlePetNightPrice : " + middlePetNightPrice);
		
		String bigPetDayPrice = CmmUtil.nvl((String)param.get("bigPetDayPrice"));
		log.info("bigPetDayPrice : " + bigPetDayPrice);
		
		String bigPetNightPrice = CmmUtil.nvl((String)param.get("bigPetNightPrice"));
		log.info("bigPetNightPrice : " + bigPetNightPrice);
		
		String refundAccountName = CmmUtil.nvl((String)param.get("refundAccountName"));
		refundAccountName = AES256Util.strEncode(refundAccountName);
		log.info("refundAccountName : " + refundAccountName);
		
		String refundBank = CmmUtil.nvl((String)param.get("refundBank"));
		refundBank = AES256Util.strEncode(refundBank);
		log.info("refundBank : " + refundBank);
		
		String refundAccountNumber = CmmUtil.nvl((String)param.get("refundAccountNumber"));
		refundAccountNumber = AES256Util.strEncode(refundAccountNumber);
		log.info("refundAccountNumber : " + refundAccountNumber);
		
		String necessaryItem = CmmUtil.nvl((String)param.get("necessaryItem"));
		log.info("necessaryItem : " + necessaryItem);
		
		String petSitterIntroduce = CmmUtil.nvl((String)param.get("petSitterIntroduce"));
		log.info("petSitterIntroduce : " + petSitterIntroduce);

		PetSitterDTO p = new PetSitterDTO();
		p.setPetSitterNo(petSitterNo);
		p.setUserNo(userNo);
		p.setPetSitterName(petSitterName);
		p.setPetSitterIntroduceOneLine(petSitterIntroduceOneLine);
		p.setPetSitterEnv(petSitterEnv);
		p.setPetSitterHasPet(petSitterHasPet);
		p.setLongTermAvailable(longTermAvailable);
		p.setWalkAvailable(walkAvailable);
		p.setBathAvailable(bathAvailable);
		p.setFirstaidAvailable(firstaidAvailable);
		p.setHaircareAvailable(haircareAvailable);
		p.setMarkingImpossible(markingImpossible);
		p.setBowelImpossible(bowelImpossible);
		p.setAttackImpossible(attackImpossible);
		p.setSeparationImpossible(separationImpossible);
		p.setBiteImpossible(biteImpossible);
		p.setSmallPetDayPrice(smallPetDayPrice);
		p.setSmallPetNightPrice(smallPetNightPrice);
		p.setMiddlePetDayPrice(middlePetDayPrice);
		p.setMiddlePetNightPrice(middlePetNightPrice);
		p.setBigPetDayPrice(bigPetDayPrice);
		p.setBigPetNightPrice(bigPetNightPrice);
		p.setRefundAccountName(refundAccountName);
		p.setRefundAccountNumber(refundAccountNumber);
		p.setRefundBank(refundBank);
		p.setNecessaryItem(necessaryItem);
		p.setPetSitterIntroduce(petSitterIntroduce);
		
		boolean result = petSitterService.updatePetSitterInfo(p);
		System.out.println(result);
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		log.info(this.getClass() + ".updatePetSitterProc end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/petSitter/getPetSitterReservationInfo", method=RequestMethod.POST)
	public @ResponseBody PetSitterDTO getPetSitterReservationInfo (@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getPetSitterReservationInfo start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		PetSitterDTO pDTO = petSitterService.getPetSitterInfo(userNo);
		
		log.info(this.getClass() + ".getPetSitterReservationInfo end!!!");
		return pDTO;
	}
	
	@RequestMapping(value="/petSitter/startReservationExposure", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> startReservationExposure(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".startReservationExposure start!!!");
		
		String petSitterNo = CmmUtil.nvl((String)param.get("petSitterNo"));
		log.info("petSitterNo : " + petSitterNo);
		String nightCheckIn = CmmUtil.nvl((String)param.get("nightCheckIn"));
		log.info("nightCheckIn : "+ nightCheckIn);
		String nightCheckOut = CmmUtil.nvl((String)param.get("nightCheckOut"));
		log.info("nightCheckOut : " + nightCheckOut);
		String dayCareStart = CmmUtil.nvl((String)param.get("dayCareStart"));
		log.info("dayCareStart : " + dayCareStart);
		String dayCareEnd = CmmUtil.nvl((String)param.get("dayCareEnd"));
		log.info("dayCareEnd : " + dayCareEnd);
		
		PetSitterDTO pDTO = new PetSitterDTO();
		pDTO.setPetSitterNo(petSitterNo);
		pDTO.setNightCheckIn(nightCheckIn);
		pDTO.setNightCheckOut(nightCheckOut);
		pDTO.setDayCareStart(dayCareStart);
		pDTO.setDayCareEnd(dayCareEnd);
		pDTO.setExposure("true");
		
		boolean result = petSitterService.updateReservationExposureStart(pDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		
		log.info(this.getClass() + ".startReservationExposure end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/petSitter/stopReservationExposure", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> stopReservationExposure(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".stopReservationExposure start!!!");
		
		String petSitterNo = CmmUtil.nvl((String)param.get("petSitterNo"));
		log.info("petSitterNo : " + petSitterNo);
		
		boolean result = petSitterService.updateReservationExposureStop(petSitterNo);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		
		log.info(this.getClass() + ".stopReservationExposure end!!!");
		return resultMap;
	}
	
}
