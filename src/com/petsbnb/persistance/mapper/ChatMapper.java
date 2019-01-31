package com.petsbnb.persistance.mapper;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.ChatDTO;

@Mapper("ChatMapper")
public interface ChatMapper {

	ChatDTO getToken(String tokenUserNo) throws Exception;
}
