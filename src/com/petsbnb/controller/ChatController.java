package com.petsbnb.controller;

import java.util.HashMap;

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
		
		ChatDTO cDTO = chatService.getToken(req);
		
		log.info(this.getClass().getName() + " end!!");
		return cDTO;
	}
}
