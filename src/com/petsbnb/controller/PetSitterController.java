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
		
		log.info(this.getClass() + ".petSitterRegProc end!!!");
		return null;
	}
	
}
