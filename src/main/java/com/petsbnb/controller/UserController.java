package com.petsbnb.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.springframework.beans.factory.annotation.Autowired;
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
import com.petsbnb.dto.PetSitterApplyDTO;
import com.petsbnb.dto.UserDTO;
import com.petsbnb.persistance.mapper.UserMapper;
import com.petsbnb.service.IUserService;
import com.petsbnb.util.AES256Util;
import com.petsbnb.util.CmmUtil;
import com.petsbnb.util.Email;
import com.petsbnb.util.EmailSender;

@Controller
public class UserController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserService")
	private IUserService userService;
	
	@Autowired
	private EmailSender emailSender;
	
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
		String deviceToken = CmmUtil.nvl((String)params.get("deviceToken"));
		log.info("deviceToken : " + deviceToken);
		email = AES256Util.strEncode(email);
		password = AES256Util.strEncode(password);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserEmail(email);
		uDTO.setUserPassword(password);
		uDTO.setDeviceToken(deviceToken);
		uDTO = userService.getUserLogin(uDTO);
		
		
		Map<Object, Object> resultMap = new HashMap<>();
		if(uDTO!= null){
			userNo = uDTO.getUserNo() + "";
			loginSuccess = true;
		}
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
	
	@RequestMapping(value="/user/getUserImage", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> getUserImage(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getUserImage start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		UserDTO udto = new UserDTO();
		udto.setUserNo(userNo);
		
		FileDTO fDTO = userService.getUserImage(udto);		
		
		Map<Object, Object> resultMap = new HashMap<>();
		if(fDTO != null){
			resultMap.put("result", true);
			resultMap.put("fileInfo", fDTO);
		}else{
			resultMap.put("result", false);
		}
		
		log.info(this.getClass() + ".getUserImage end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/userFindEmail.do", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> userFindEmail(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".userFindEmail start!!!!");
		
		String name = CmmUtil.nvl((String)param.get("name"));
		log.info("name : " + name);
		name = AES256Util.strEncode(name);
		String phone = CmmUtil.nvl((String)param.get("phone"));
		log.info("phone : " + phone);
		phone=AES256Util.strEncode(phone);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserName(name);
		uDTO.setUserPhone(phone);
		
		uDTO = userService.getUserFindEmail(uDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		if(uDTO == null){
			resultMap.put("result", false);
		}else{
			resultMap.put("result", true);
			resultMap.put("userEmail", AES256Util.strDecode(uDTO.getUserEmail()));
		}
		System.out.println(resultMap.toString());
		log.info(this.getClass() + ".userFindEmail end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/userFindPassword", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> userFindPassword(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".userFindPassword start!!!");
		
		String email = CmmUtil.nvl((String)param.get("email"));
		email = AES256Util.strEncode(email);
		log.info("email : " + email);
		String name = CmmUtil.nvl((String)param.get("name"));
		name = AES256Util.strEncode(name);
		log.info("name : " + name);
		String phone = CmmUtil.nvl((String)param.get("phone"));
		phone = AES256Util.strEncode(phone);
		log.info("phone : " + phone);
		
		String tmpPass = CmmUtil.createTmpPassword();
		tmpPass = AES256Util.strEncode(tmpPass);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserEmail(email);
		uDTO.setUserPhone(phone);
		uDTO.setUserName(name);
		uDTO.setUserPassword(tmpPass);
		
		uDTO = userService.getUserFindPassword(uDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		
		if(uDTO == null){
			resultMap.put("result", false);
		}else{
			Email sendEmail = new Email();
			sendEmail.setReciver(AES256Util.strDecode(uDTO.getUserEmail()));
			sendEmail.setSubject("Petsbnb 임시비밀번호 입니다.");
			sendEmail.setContent(AES256Util.strDecode(uDTO.getUserPassword()));
			emailSender.SendEmail(sendEmail);
			resultMap.put("result", true);
		}
		
		log.info(this.getClass() + ".userFindPassword end!!!");
		return resultMap;
		
	}
	
	@RequestMapping(value="/user/checkPetSitter", method=RequestMethod.POST)
	public @ResponseBody UserDTO checkPetSitter(@RequestBody Map<Object,Object> param) throws Exception{
		log.info(this.getClass() + ".checkPetSitter start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserNo(userNo);
		
		uDTO = userService.getCheckPetSitter(uDTO);
		
		log.info(this.getClass() + ".checkPetSitter end!!!");
		return uDTO;
	}
	
	@RequestMapping(value="/user/getUserAddress", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> getUserAddress(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".getUserAddress start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserNo(userNo);
		
		uDTO = userService.getUserAddress(uDTO);
		String userAddress = uDTO.getUserAddress();
		String userAddressDetail = uDTO.getUserAddressDetail();
		
		userAddress += " " + userAddressDetail;
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("userAddress", userAddress);
		
		log.info(this.getClass() + ".getUserAddress end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/applyPetSitter", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> applyPetSitter(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".applyPetSitter start!!!");
		String name = CmmUtil.nvl((String)param.get("name"));
		log.info("name : " + name);
		name = AES256Util.strEncode(name);
		String gender = CmmUtil.nvl((String)param.get("gender"));
		log.info("gender : " + gender);
		String birthday = CmmUtil.nvl((String)param.get("birthday"));
		log.info("birthday : " + birthday);
		birthday = AES256Util.strEncode(birthday);
		String phone = CmmUtil.nvl((String)param.get("phone"));
		log.info("phone : " + phone);
		phone = AES256Util.strEncode(phone);
		String job = CmmUtil.nvl((String)param.get("job"));
		log.info("job : " + job);
		job = AES256Util.strEncode(job);
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		String email = CmmUtil.nvl((String)param.get("email"));
		log.info("email : " + email);
		email = AES256Util.strEncode(email);
		
		PetSitterApplyDTO pDTO = new PetSitterApplyDTO();
		pDTO.setApplyName(name);
		pDTO.setApplyGender(gender);
		pDTO.setApplyBirthday(birthday);
		pDTO.setApplyPhone(phone);
		pDTO.setApplyJob(job);
		pDTO.setUserNo(userNo);
		pDTO.setApplyEmail(email);
		
		int insertResult = userService.insertPetSitterApply(pDTO);
		
		Map<Object, Object> resultMap = new HashMap<>();
		if(insertResult != 0){
			resultMap.put("result", true);
		}else{
			resultMap.put("result", false);
		}
		log.info(this.getClass() + ".applyPetSitter end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/checkAppliedUser", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> checkAppliedUser(@RequestBody Map<Object, Object> param) throws Exception{
		log.info(this.getClass() + ".checkAppliedUser start!!!");
		
		String userNo = CmmUtil.nvl((String)param.get("userNo"));
		log.info("userNo : " + userNo);
		
		PetSitterApplyDTO pDTO = userService.checkAppliedUser(userNo);
		
		Map<Object, Object> resultMap = new HashMap<>();
		
		if(pDTO != null){
			resultMap.put("result", true);
		}else{
			resultMap.put("result", false);
		}
		
		log.info(this.getClass() + ".checkAppliedUser end!!!");
		return resultMap;
	}
	
	@RequestMapping(value="/user/fcmTest")
	public void fcmTest(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass() + ".fcmTest start!!!");
		
		final String apiKey = "AAAAJuOsoX4:APA91bGVJeBud0Ski4Iba1nQxGMm842QsZ4xVKB7lPv_l82h6rqEbqbayPUnWN3RXntBOEgPrEhqhKyxRnuc_7xsrGIMJNLO5nyf5zgKo_2pms8v9QHMMbFH5wxxdQezwgV0wCO-YvZg";
		
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);
        conn.setDoOutput(true);

        String input = "{\"notification\" : {\"title\" : \" 새로운 펫시팅 요청 \", \"body\" : \"박수용님이 펫시팅을 요청했습니다.\"}, \"to\":\"cF3Gncbtshw:APA91bF1VsMz2k_l8FInsBmPIQ-Jecjido7F1qSH4KbAnCuK2SF1XgNUB4529JDVoiyY96TjAUJHibSF14eDQ3LhCcLi0Xn_y6048DhiAZ8Kq6_-GAswd-AHoDevSjLCFE5Ofic6uZIH\"}";

        OutputStream os = conn.getOutputStream();
        
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println(response.toString());        
		log.info(this.getClass() + ".fcmTest end!!!");
	}
	
	@RequestMapping(value="/user/auth")
	public String auth(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session)throws Exception{
		log.info(this.getClass() + ".auth start!!!");
		
		log.info(this.getClass() + ".auth end!!!");
		return "/user/auth";
	}
	
}
