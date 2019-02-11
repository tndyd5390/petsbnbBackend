package com.petsbnb.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.ChatDTO;
import com.petsbnb.persistance.mapper.ChatMapper;
import com.petsbnb.service.IChatService;

@Service("ChatService")
public class ChatService implements IChatService{

	@Resource(name="ChatMapper")
	private ChatMapper chatMapper;

	@Override
	public ChatDTO getToken(HashMap<Object, Object> req) throws Exception {
		
		String nowUserNo = req.get("nowUserNo").toString();
		String propsUserNo = req.get("propsUserNo").toString();
		String petsitterUserNo = req.get("petsitterUserNo").toString();
		String tokenUserNo = "";
		
		System.out.println(nowUserNo);
		System.out.println(propsUserNo);
		System.out.println(petsitterUserNo);
		
		if(nowUserNo.equals(petsitterUserNo)) {
			tokenUserNo = propsUserNo;
		}else {
			tokenUserNo = petsitterUserNo;
		}
		
		return chatMapper.getToken(tokenUserNo);
	}

}
