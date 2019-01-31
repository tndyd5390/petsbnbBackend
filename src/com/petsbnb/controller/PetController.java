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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		String petBirthday = CmmUtil.nvl(req.getParameter("petBirthday"));
		log.info("petBirthday : " + petBirthday);
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
		pDTO.setPetBirthday(petBirthday);
		pDTO.setPetWeight(petWeight);
		pDTO.setPetNeutralization(petNeutralization);
		pDTO.setPetUnfamiliar(petUnfamiliar);
		pDTO.setPetMeetAnotherPet(petMeetAnotherPet);
		pDTO.setPetBarks(petBarks);
		pDTO.setPetBowelTraining(petBowelTraining);
		pDTO.setPetComprehensiveVaccine(petComprehensiveVaccine);
		pDTO.setPetRabiesvaccination(petRabiesVaccination);
		pDTO.setPetHeartworm(petHeartWorm);
		pDTO.setPetCoronaenteritis(petCoronaEnteritis);
		pDTO.setPetKennelkov(petKennelkov);
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
	
	@RequestMapping(value="/pet/getPetList")
	public @ResponseBody List<PetDTO> getPetList(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getList start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		List<PetDTO> pList = petService.getPetList(userNo);
		log.info(this.getClass() + ".getList end!!!");
		return pList;
	}
	
	@RequestMapping(value="/pet/getPetInfo")
	public @ResponseBody Map<String, Object> getPetInfo(@RequestBody Map<Object,Object> param) throws Exception{
		log.info(this.getClass() + ".getPetInfo start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		String petNo = CmmUtil.nvl((String)param.get("petNo"));
		log.info("petNo");
		
		PetDTO pDTO = new PetDTO();
		pDTO.setUserNo(userNo);
		pDTO.setPetNo(petNo);
		
		Map<String, Object> result = petService.getPetInfo(pDTO);
		
		log.info(this.getClass() + ".getPetInfo end!!!");
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value="/pet/deletePetImage", method=RequestMethod.POST)
	public @ResponseBody List<PetFileDTO> deleteAndGetPetImage(@RequestBody Map<Object,Object> param) throws Exception{
		log.info(this.getClass() + ".deleteAndGetPetImage Start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		String petFileNo = CmmUtil.nvl((String)param.get("petFileNo"));
		log.info("petFileNo : " + petFileNo);
		String petNo = CmmUtil.nvl((String)param.get("petNo"));
		log.info("petNo : " + petNo);
		
		PetFileDTO pfDTO = new PetFileDTO();
		pfDTO.setUserNo(userNo);
		pfDTO.setPetNo(petNo);
		pfDTO.setPetFileNo(petFileNo);
		
		List<PetFileDTO> petFileList = petService.deleteImageAndGetImage(pfDTO);
		
		log.info(this.getClass() + ".deleteAndGetPetImage end!!!");
		return petFileList;
	}
	
	@RequestMapping(value="/pet/petImageUploadSep")
	public @ResponseBody List<PetFileDTO> petImageUploadSep(MultipartHttpServletRequest req) throws Exception{
		log.info(this.getClass() + ".petImageUploadSep start!!!");
		String userNo = CmmUtil.nvl((String)req.getParameter("userNo"));
		log.info("userNo : " + userNo);
		String petNo = CmmUtil.nvl((String)req.getParameter("petNo"));
		log.info("petNo : " + petNo);
		
		MultipartFile file = req.getFile("petImage");
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
		pfDTO.setPetNo(petNo);
		
		List<PetFileDTO> petFileList = petService.insertImageAndGetImage(pfDTO);
		
		log.info(this.getClass() + ".petImageUploadSep end!!!");
		return petFileList;
	}
	
	@RequestMapping(value="/pet/updatePetProfile", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> updatePetProfile(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".updatePetProfile start!!!");
		String petNo = CmmUtil.nvl((String)param.get("petNo"));
		log.info("petNo : " + petNo);
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		String petBirthday = CmmUtil.nvl((String)param.get("petBirthday"));
		log.info("petBirthday : " + petBirthday);
		String petName = CmmUtil.nvl((String)param.get("petName"));
		log.info("petName : " + petName);
		String petGender = CmmUtil.nvl((String)param.get("petGender"));
		log.info("petGender : " + petGender);
		String petKind = CmmUtil.nvl((String)param.get("petKind"));
		log.info("petKind : " + petKind);
		String petWeight = CmmUtil.nvl((String)param.get("petWeight"));
		log.info("petWeight : " + petWeight);
		String petNeutralization = CmmUtil.nvl((String)param.get("petNeutralization"));
		log.info("petNeutralization : " + petNeutralization);
		String petUnfamiliar = CmmUtil.nvl((String)param.get("petUnfamiliar"));
		log.info("petUnfamiliar : " + petUnfamiliar);
		String petMeetAnotherPet = CmmUtil.nvl((String)param.get("petMeetAnotherPet"));
		log.info("petMeetAnotherPet : " + petMeetAnotherPet);
		String petBarks = CmmUtil.nvl((String)param.get("petBarks"));
		log.info("petBarks : " + petBarks);
		String petBowelTraining = CmmUtil.nvl((String)param.get("petBowelTraining"));
		log.info("petBowelTraining : " + petBowelTraining);
		String petComprehensiveVaccine = CmmUtil.nvl((String)param.get("petComprehensiveVaccine"));
		log.info("petComprehensiveVaccine : " + petComprehensiveVaccine);
		String petRabiesVaccination = CmmUtil.nvl((String)param.get("petRabiesVaccination"));
		log.info("petRabiesVaccination : " + petRabiesVaccination);
		String petHeartWorm = CmmUtil.nvl((String)param.get("petHeartWorm"));
		log.info("petHeartWorm : " + petHeartWorm);
		String petCoronaEnteritis = CmmUtil.nvl((String)param.get("petCoronaEnteritis"));
		log.info("petCoronaEnteritis : " + petCoronaEnteritis);
		String petKennelkov = CmmUtil.nvl((String)param.get("petKennelkov"));
		log.info("petKennelkov : " + petKennelkov);
		String petNoneVaccine = CmmUtil.nvl((String)param.get("petNoneVaccine"));
		log.info("petNoneVaccine : " + petNoneVaccine);
		String petSpecialMatters = CmmUtil.nvl((String)param.get("petSpecialMatters"));
		log.info("petSpecialMatters : " + petSpecialMatters);
		String petReference = CmmUtil.nvl((String)param.get("petReference"));
		log.info("petReference : " + petReference);
		
		PetDTO pDTO = new PetDTO();
		pDTO.setPetNo(petNo);
		pDTO.setUserNo(userNo);
		pDTO.setPetBirthday(petBirthday);
		pDTO.setPetName(petName);
		pDTO.setPetGender(petGender);
		pDTO.setPetKind(petKind);
		pDTO.setPetWeight(petWeight);
		pDTO.setPetNeutralization(petNeutralization);
		pDTO.setPetUnfamiliar(petUnfamiliar);
		pDTO.setPetMeetAnotherPet(petMeetAnotherPet);
		pDTO.setPetBarks(petBarks);
		pDTO.setPetBowelTraining(petBowelTraining);
		pDTO.setPetComprehensiveVaccine(petComprehensiveVaccine);
		pDTO.setPetRabiesvaccination(petRabiesVaccination);
		pDTO.setPetHeartworm(petHeartWorm);
		pDTO.setPetCoronaenteritis(petCoronaEnteritis);
		pDTO.setPetKennelkov(petKennelkov);
		pDTO.setPetNonevaccine(petNoneVaccine);
		pDTO.setPetSpecialMatters(petSpecialMatters);
		pDTO.setPetReference(petReference);
		System.out.println(pDTO.toString());
		log.info(this.getClass() + ".updatePetProfile end!!!");
		
		int result = petService.updatePetProfile(pDTO);
		Map<Object, Object> resultMap = new HashMap<>();
		if(result != 0){
			resultMap.put("result", true);
		}else{
			resultMap.put("result", false);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value="/pet/deletePetProfile", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> deletePetProfile(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".deletePetProfile start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		String petNo = CmmUtil.nvl((String)param.get("petNo"));
		log.info("petNo : " + petNo);
		
		PetDTO pDTO = new PetDTO();
		pDTO.setUserNo(userNo);
		pDTO.setPetNo(petNo);
		
		boolean result = petService.deletePetProfile(pDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		log.info(this.getClass() + ".deletePetProfile end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/pet/getSelectedPetList", method=RequestMethod.POST)
	public @ResponseBody List<PetDTO> getSelectedPetList(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getSelectedPetList start!!!");
		
		List<String> selectedPetNo = (List<String>)param.get("petNoArr");
		
		Map<String, Object> selectedPetMap = new HashMap<>();
		selectedPetMap.put("selectedPetNo", selectedPetNo);
		
		List<PetDTO> pList = petService.getSelectedPetList(selectedPetMap);
		
		log.info(this.getClass() + ".getSelectedPetList end!!!");
		return pList;
	}
	
	@RequestMapping(value="/pet/getAvaliablePetList", method=RequestMethod.POST)
	public @ResponseBody List<PetDTO> getAvaliablePetList(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getAvaliablePetList start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		List<String> avaliablePetKindList = (List<String>)param.get("availablePetKind");
		if(avaliablePetKindList.size() == 0){
			avaliablePetKindList.add("NONE");
		}
		Map<String, Object> avaliablePetKind = new HashMap<>();
		avaliablePetKind.put("avaliablePetKindList", avaliablePetKindList);
		avaliablePetKind.put("userNo", userNo);
		
		List<PetDTO> pList = petService.getAvaliablePetList(avaliablePetKind);
		
		log.info(this.getClass() + ".getAvaliablePetList end!!!");
		return pList;
	}
}
