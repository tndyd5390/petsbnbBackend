package com.petsbnb.controller;

import java.io.File;
import java.util.HashMap;
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

import com.petsbnb.service.ITimelineService;
import com.petsbnb.util.CmmUtil;
import com.petsbnb.util.NLPProcess;

@Controller
public class TimelineController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="TimelineService")
	private ITimelineService timelineService;
	
	final String timelineFilePath = "C:\\Users\\DATA16\\git\\petsbnbBackend\\WebContent\\timelineFile\\";
	
	
	@RequestMapping(value="/timeline/getTimelineList", method=RequestMethod.POST)
	public @ResponseBody List<Map<Object, Object>> getTimelineList(@RequestBody Map<Object, Object> param) throws Exception{
		
		log.info(this.getClass() + ".getTimelineList start!!!");
		
		String reservationNo = CmmUtil.nvl((String)param.get("reservationNo"));
		log.info("reservationNo : " + reservationNo);
		
		List<Map<Object, Object>> timelineList = timelineService.getTimeList(reservationNo);
		
		log.info(this.getClass() + ".getTimelineList end!!!");
		return timelineList;
	}
	
	@RequestMapping(value="/timeline/uploadTimeline", method=RequestMethod.POST)
	public @ResponseBody Map<Object, Object> uploadTimeline(MultipartHttpServletRequest req) throws Exception{
		
		log.info(this.getClass() + ".uploadTimeline start!!!");
		
		String userNo = CmmUtil.nvl(req.getParameter("userNo"));
		log.info("userNo : " + userNo);
		String reservationNo = CmmUtil.nvl(req.getParameter("reservationNo"));
		log.info("reservationNo : " + reservationNo);
		String content = CmmUtil.nvl(req.getParameter("content"));
		log.info("content : " + content);
		
		NLPProcess np = new NLPProcess();
		int emotionRate = np.NLPrun(content);
		
		MultipartFile file = req.getFile("timelineFile");
		
		String fileOrgName = file.getOriginalFilename();
		log.info("fileOrgName : " + fileOrgName);
		String fileType = file.getContentType();
		String extended = "." + fileType.substring(fileType.indexOf("/") + 1, fileType.length());
		log.info(this.getClass() + " fileName " + fileOrgName + "." + fileType);
		
		String reFileName = timelineFilePath + fileOrgName;
		System.out.println("reFileName : " + reFileName);
		File newFile = new File(reFileName);
		file.transferTo(newFile);
		Map<String, String> param = new HashMap<>();
		param.put("reservationNo", reservationNo);
		param.put("userNo", userNo);
		param.put("content", content);
		param.put("fileName", fileOrgName);
		param.put("filePath", timelineFilePath);
		param.put("emotionRate", emotionRate + "");
		
		boolean result = timelineService.insertTimeline(param);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		
		log.info(this.getClass() + ".uploadTimeline end!!!");
		
		return resultMap;
	}
}
