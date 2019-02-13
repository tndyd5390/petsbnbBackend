package com.petsbnb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.ChatDTO;
import com.petsbnb.persistance.mapper.ChatMapper;
import com.petsbnb.service.IChatService;
import com.petsbnb.util.CmmUtil;
import com.petsbnb.util.DecodeUtil;

@Service("ChatService")
public class ChatService implements IChatService{

	@Resource(name="ChatMapper")
	private ChatMapper chatMapper;

	@Override
	public ChatDTO getToken(HashMap<Object, Object> req) throws Exception {
		
		String nowUserNo = CmmUtil.nvl(req.get("nowUserNo").toString());
		String propsUserNo = CmmUtil.nvl(req.get("propsUserNo").toString());
		String petsitterUserNo = CmmUtil.nvl(req.get("petsitterUserNo").toString());
		String tokenUserNo = "";
		
		if(nowUserNo.equals(petsitterUserNo)) {
			tokenUserNo = propsUserNo;
		}else {
			tokenUserNo = petsitterUserNo;
		}
		
		return chatMapper.getToken(tokenUserNo);
	}

	@Override
	public void createChatRoom(HashMap<Object, Object> req) throws Exception {
		try {
			chatMapper.insertChatRoom(req);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<HashMap<Object, Object>> getChatList(HashMap<Object, Object> req) throws Exception {
		List<HashMap<Object, Object>> rslt = new ArrayList();
		try {
			rslt = DecodeUtil.decodeName(chatMapper.getChatList(req));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rslt;
	}

}
