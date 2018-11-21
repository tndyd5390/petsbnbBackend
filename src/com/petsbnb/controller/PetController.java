package com.petsbnb.controller;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petsbnb.service.IPetService;
import com.petsbnb.util.CmmUtil;

@Controller
public class PetController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="PetService")
	private IPetService petService;
	
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
		
		Iterator<String> fileNames =  req.getFileNames();

		while(fileNames.hasNext()){
			System.out.println(fileNames.next());
		}
		
		log.info(this.getClass() + ".petProfileRegProc end!!!");
		return null;
	}
}
