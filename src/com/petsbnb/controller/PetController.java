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
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petsbnb.dto.FileDTO;
import com.petsbnb.dto.PetDTO;
import com.petsbnb.dto.PetFileDTO;
import com.petsbnb.service.IPetService;
import com.petsbnb.util.CmmUtil;

@Controller
public class PetController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="PetService")
	private IPetService petService;
	
	String petImageFilePath = "C:\\Users\\DATA16\\git\\petsbnbBackend\\WebContent\\petImageFile\\";
	
	@RequestMapping(value="/pet/petProfileRegProc")
	public @ResponseBody Map<Object, Object> petProfileRegProc(MultipartHttpServletRequest req) throws Exception{
		log.info(this.getClass() + ".petProfileRegProc start!!!");
		
		String userNo = CmmUtil.nvl(req.getParameter("userNo"));
		log.info("userNo : " + userNo);
		String petName = CmmUtil.nvl(req.getParameter("petName"));
		log.info("petName : " + petName);
		String petGender = CmmUtil.nvl(req.getParameter("petGender"));
		log.info("petGender : " + petGender);
		String petKind = CmmUtil.nvl(req.getParameter("petKind"));
		log.info("petKind : " + petKind);
		String petWeight = CmmUtil.nvl(req.getParameter("petWeight"));
		log.info("petWeight : " + petWeight);
		String petNeutralization = CmmUtil.nvl(req.getParameter("petNeutralization"));
		log.info("petNeutrallzation : " + petNeutralization);
		String petUnfamiliar = CmmUtil.nvl(req.getParameter("petUnfamiliar"));
		log.info("petUnfamiliar : " + petUnfamiliar);
		String petMeetAnotherPet = CmmUtil.nvl(req.getParameter("petMeetAnotherPet"));
		log.info("petMeetAnotherPet : " + petMeetAnotherPet);
		String petBarks = CmmUtil.nvl(req.getParameter("petBarks"));
		log.info("petBarks : " + petBarks);
		String petBowelTraining = CmmUtil.nvl(req.getParameter("petBowelTraining"));
		log.info("petBowelTraining : " + petBowelTraining);
		String petComprehensiveVaccine = CmmUtil.nvl(req.getParameter("petComprehensiveVaccine"));
		log.info("petComprehensiveVaccine : " + petComprehensiveVaccine);
		String petRabiesVaccination = CmmUtil.nvl(req.getParameter("petRabiesVaccination"));
		log.info("petRabiesVaccination : " + petRabiesVaccination);
		String petHeartWorm = CmmUtil.nvl(req.getParameter("petHeartWorm"));
		log.info("petHeartWorm : "+ petHeartWorm);
		String petCoronaEnteritis = CmmUtil.nvl(req.getParameter("petCoronaEnteritis"));
		log.info("petCoronaEnteritis : " + petCoronaEnteritis);
		String petKennelkov = CmmUtil.nvl(req.getParameter("petKennelkov"));
		log.info("petKennelkov : " + petKennelkov);
		String petNoneVaccine = CmmUtil.nvl(req.getParameter("petNoneVaccine"));
		log.info("petNoneVaccine : " + petNoneVaccine);
		String petSpecialMatters = CmmUtil.nvl(req.getParameter("petSpecialMatters"));
		log.info("petSpecialMatters : " + petSpecialMatters);
		String petReference = CmmUtil.nvl(req.getParameter("petReference"));
		log.info("petReference : " + petReference);
		
		PetDTO pDTO = new PetDTO();
		pDTO.setUserNo(userNo);
		pDTO.setPetName(petName);
		pDTO.setPetGender(petGender);
		pDTO.setPetKind(petKind);
		pDTO.setPetWeight(petWeight);
		pDTO.setPetNeutralization(petNeutralization);
		pDTO.setPetUnfamiliar(petUnfamiliar);
		pDTO.setpetMeetAnotherPet(petMeetAnotherPet);
		pDTO.setPetBarks(petBarks);
		pDTO.setpetBowelTraining(petBowelTraining);
		pDTO.setPetComprehensiveVaccine(petComprehensiveVaccine);
		pDTO.setPetRabiesvaccination(petRabiesVaccination);
		pDTO.setPetHeartworm(petHeartWorm);
		pDTO.setPetCoronaenteritis(petCoronaEnteritis);
		pDTO.setpetKennelkov(petKennelkov);
		pDTO.setPetNonevaccine(petNoneVaccine);
		pDTO.setPetSpecialMatters(petSpecialMatters);
		pDTO.setPetReference(petReference);
		
		List<PetFileDTO> ptList = new ArrayList<>();
		
		Iterator<String> fileNames =  req.getFileNames();
		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile file = req.getFile(fileName);
			
			String reFileName = "";
			String fileOrgName = file.getOriginalFilename();
			String fileType = file.getContentType();
			log.info(this.getClass() + ".file.getOriginalFilename() : " + file.getOriginalFilename());
			
			String extended = "." + fileType.substring(fileType.indexOf("/") + 1, fileType.length());
			String now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
			
			reFileName = petImageFilePath + now + extended;
			
			File newFile = new File(reFileName);
			file.transferTo(newFile);
	
			PetFileDTO pfDTO = new PetFileDTO();
			pfDTO.setPetFileOrgName(fileOrgName);
			pfDTO.setPetFileName(now + extended);
			pfDTO.setPetFilePath(petImageFilePath);
			pfDTO.setRegNo(userNo);
			pfDTO.setUserNo(userNo);
			
			ptList.add(pfDTO);
		}
		
		Map<String, Object> pfMap = new HashMap<>();
		pfMap.put("petImages", ptList);
		
		
		boolean result = petService.insertPetProfile(pfMap, pDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		
		log.info(this.getClass() + ".petProfileRegProc end!!!");
		return resultMap;
		
	}
}
