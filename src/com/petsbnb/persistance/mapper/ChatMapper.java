package com.petsbnb.persistance.mapper;

import java.util.HashMap;
import java.util.List;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.ChatDTO;

@Mapper("ChatMapper")
public interface ChatMapper {

	ChatDTO getToken(String tokenUserNo) throws Exception;

	void insertChatRoom(HashMap<Object, Object> req) throws Exception;

	List<HashMap<Object, Object>> getChatList(HashMap<Object, Object> req) throws Exception;

}
