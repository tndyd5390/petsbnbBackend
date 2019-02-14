package com.petsbnb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petsbnb.dto.ChatDTO;
import com.petsbnb.service.IChatService;

@Controller
public class ChatController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="ChatService")
	private IChatService chatService;
	
	@RequestMapping("/chat/getToken")
	public @ResponseBody ChatDTO getToken(@RequestBody HashMap<Object, Object> req) throws Exception{
		log.info(this.getClass().getName() + " start!!");
		System.out.println(req.get("nowUserNo"));
		System.out.println(req.get("propsUserNo"));
		System.out.println(req.get("petsitterUserNo"));
		ChatDTO cDTO = chatService.getToken(req);
		
		log.info(this.getClass().getName() + " end!!");
		return cDTO;
	}
	
	@RequestMapping("/chat/createChatRoom")
	public void createChatRoom(@RequestBody HashMap<Object, Object> req) throws Exception{
		log.info(this.getClass().getName() + " start!!");
		
		chatService.createChatRoom(req);
		
		log.info(this.getClass().getName() + " end!!");
	}
	
	@RequestMapping("/chat/chatList")
	public @ResponseBody List<HashMap<Object, Object>> chatList(@RequestBody HashMap<Object, Object> req) throws Exception{
		log.info(this.getClass().getName() + " start!!");
		
		log.info("userNo : "+ req.get("userNo").toString());
		
		List<HashMap<Object, Object>> rslt = chatService.getChatList(req);
		
		log.info(this.getClass().getName() + " end!!");
		return rslt;
	}
}
