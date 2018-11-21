package com.petsbnb.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.petsbnb.dto.FileDTO;
import com.petsbnb.dto.UserDTO;
import com.petsbnb.service.IUserService;
import com.petsbnb.util.AES256Util;
import com.petsbnb.util.CmmUtil;

@Controller
public class UserController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserService")
	private IUserService userService;
	
	String userImageFilePath = "C:\\Users\\DATA16\\git\\petsbnbBackend\\WebContent\\userImageFile\\";
	
	@RequestMapping(value="/user/daumPostView")
	public ModelAndView daumPostView(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass() + ".daumPostView start!!!");
		
		log.info(this.getClass() + ".daumPostView end!!!");
		ModelAndView mv = new ModelAndView("/user/daumPostView");
		return mv;
	}
	
	@RequestMapping(value="/user/userRegProc", method=RequestMethod.POST)
	public @ResponseBody UserDTO userRegProc(@RequestBody Map<Object, Object> param, HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass() + ".userRegProc start!!!");
		String email = CmmUtil.nvl( (String)param.get("email"));
		String name = CmmUtil.nvl((String)param.get("name"));
		String phoneNumber = CmmUtil.nvl((String)param.get("phoneNumber"));
		String password = CmmUtil.nvl((String)param.get("password"));
		String zipCode = CmmUtil.nvl((String)param.get("zipCode"));
		String address = CmmUtil.nvl((String)param.get("address"));
		String addressDetail = CmmUtil.nvl((String)param.get("addressDetail"));
		
		log.info("email : " + email);
		log.info("name : " + name);
		log.info("phoneNumber : " + phoneNumber);
		log.info("password : " + password);
		log.info("address : " + address);
		log.info("zipCode : " + zipCode);
		log.info("addressDetail : " + addressDetail);
		 
		email = AES256Util.strEncode(email);
		name = AES256Util.strEncode(name);
		phoneNumber = AES256Util.strEncode(phoneNumber);
		password = AES256Util.strEncode(password);
		zipCode = AES256Util.strEncode(zipCode);
		address = AES256Util.strEncode(address);
		addressDetail = AES256Util.strEncode(addressDetail);
		
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserEmail(email);
		uDTO.setUserName(name);
		uDTO.setUserPhone(phoneNumber);
		uDTO.setUserPassword(password);
		uDTO.setUserZipcode(zipCode);
		uDTO.setUserAddress(address);
		uDTO.setUserAddressDetail(addressDetail);
		
		uDTO = userService.insertUserReg(uDTO);
		if(uDTO.getUserNo() != null ){
			uDTO.setRegSuccess(true);
		}else{
			uDTO.setRegSuccess(false);
		}
		
		log.info(this.getClass() + ".userRegProc end!!!");
		return uDTO;
	}
	
	@RequestMapping(value="/user/loginProc", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> loginProc(@RequestBody Map<Object, Object> params) throws Exception{
		log.info(this.getClass() + ".loginProc start!!!");
		
		String userNo = "";
		boolean loginSuccess = false;
		
		String email = CmmUtil.nvl((String)params.get("email"));
		log.info("email : " + email);
		String password = CmmUtil.nvl((String)params.get("password"));
		log.info("password : " + password);
		
		email = AES256Util.strEncode(email);
		password = AES256Util.strEncode(password);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserEmail(email);
		uDTO.setUserPassword(password);
		
		uDTO = userService.getUserLogin(uDTO);
		
		
		Map<Object, Object> resultMap = new HashMap<>();
		if(uDTO!= null){
			userNo = uDTO.getUserNo() + "";
			loginSuccess = true;
		}
		System.out.println("userNo : " + userNo);
		System.out.println("loginSuccess : " + loginSuccess);
		resultMap.put("userNo", userNo);
		resultMap.put("loginSuccess", loginSuccess);
		
		log.info(this.getClass() + ".loginProc end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/emailCheck", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> emailCheck(@RequestBody Map<Object,Object> param) throws Exception{
		log.info(this.getClass() + ".emailCheck start!!!");
		
		String email = CmmUtil.nvl((String)param.get("email"));
		email = AES256Util.strEncode(email);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserEmail(email);
		
		List<UserDTO> uList = userService.getEmailCheck(uDTO);
		if(uList == null) uList = new ArrayList<>();
		
		boolean emailValid = false;
		
		if(uList.size() == 0){
			emailValid = true;
		}
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("emailValid", emailValid);
		
		log.info(this.getClass() + ".emailCheck end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/checkPassword", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> checkPassword(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".checkPasswrod start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		String password = CmmUtil.nvl((String)param.get("password"));
		log.info("password : " + password);
		
		password = AES256Util.strEncode(password);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserNo(userNo);
		uDTO.setUserPassword(password);
		
		uDTO = userService.getCheckPassword(uDTO);
		Map<Object, Object> resultMap = new HashMap<>();
		if(uDTO != null){
			resultMap.put("passwordCheck", true);
		}else{
			resultMap.put("passwordCheck", false);
		}
		
		log.info(this.getClass() + ".checkPassword end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/getUserInfo", method=RequestMethod.POST)
	public @ResponseBody UserDTO getUserInfo(@RequestBody Map<Object,Object> param) throws Exception{
		log.info(this.getClass() +".getUserInfo start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserNo(userNo);
		
		uDTO = userService.getUserInfo(uDTO);
		if(uDTO == null){
			uDTO = new UserDTO();
		}else{
			uDTO.setUserName(AES256Util.strDecode(uDTO.getUserName()));
			uDTO.setUserAddress(AES256Util.strDecode(uDTO.getUserAddress()));
			uDTO.setUserAddressDetail(AES256Util.strDecode(uDTO.getUserAddressDetail()));
			uDTO.setUserZipcode(AES256Util.strDecode(uDTO.getUserZipcode()));
			uDTO.setUserPhone(AES256Util.strDecode(uDTO.getUserPhone()));
			uDTO.setUserEmail(AES256Util.strDecode(uDTO.getUserEmail()));
		}
		log.info(this.getClass() +".getUserInfo end!!!");
		return uDTO;
	}
	
	@RequestMapping(value="/user/userUpdatePassword", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> userUpdatePassword(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".userUpdatePassword start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		String password = CmmUtil.nvl((String)param.get("password"));
		log.info("password : "+ password);
		
		password = AES256Util.strEncode(password);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserNo(userNo);
		uDTO.setUserPassword(password);
		
		int result = userService.updateUserPassword(uDTO);
		
		Map<Object,Object> resultMap = new HashMap<>();
		if(result != 0){
			resultMap.put("updatePasswordSuccess", true);
		}else{
			resultMap.put("updatePasswordSuccess", false);
		}
		
		log.info(this.getClass() + ".userUpdatePasswrod end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/userImageUpload")
	public @ResponseBody Map<Object, Object> userImageUpload(MultipartHttpServletRequest req) throws Exception{
		log.info(this.getClass() + ".userImageUpload start!!!");
		
		String userNo = CmmUtil.nvl(req.getParameter("userNo"));
		log.info("userNo : " + userNo);
		
		MultipartFile file = req.getFile("image");
		
		
		String reFileName = "";
		String fileOrgName = file.getOriginalFilename();
		String fileType = file.getContentType();
		log.info(this.getClass() + ".file.getOriginalFilename() : " + file.getOriginalFilename());
		
		String extended = "." + fileType.substring(fileType.indexOf("/") + 1, fileType.length());
		String now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
		
		reFileName = userImageFilePath + now + extended;
		
		File newFile = new File(reFileName);
		file.transferTo(newFile);
		
		FileDTO fDTO = new FileDTO();
		fDTO.setFileOrgName(fileOrgName);
		fDTO.setFileName(now + extended);
		fDTO.setFilePath(userImageFilePath);
		fDTO.setRegUserNo(userNo);
		
		boolean result = userService.updateUserImage(fDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("uploadImageSuccess", result);

		log.info(this.getClass() + ".userImageUpload end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/userUpdateAddress", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> userUpdateAddress(@RequestBody Map<Object,Object> param)throws Exception{
		log.info(this.getClass() + ".userUpdataAddress start!!!");
		
		String zipcode = CmmUtil.nvl((String)param.get("zipcode"));
		log.info("zipcode : " + zipcode);
		String address = CmmUtil.nvl((String)param.get("address"));
		log.info("address : " + address);
		String addressDetail = CmmUtil.nvl((String)param.get("addressDetail"));
		log.info("addressDetail : " + addressDetail);
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		zipcode = AES256Util.strEncode(zipcode);
		address = AES256Util.strEncode(address);
		addressDetail = AES256Util.strEncode(addressDetail);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserZipcode(zipcode);
		uDTO.setUserAddress(address);
		uDTO.setUserAddressDetail(addressDetail);
		uDTO.setUserNo(userNo);
		int result = userService.updateUserAddress(uDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		if(result != 0){
			resultMap.put("userUpdateAddressSuccess", true);
		}else{
			resultMap.put("userUpdateAddressSuccess", false);
		}
		
		log.info(this.getClass() + ".userUpdateAddress end!!!");
		return resultMap;
	}
	
}
