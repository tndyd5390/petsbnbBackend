package com.petsbnb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.petsbnb.dto.ChatDTO;
import com.petsbnb.dto.PetDTO;
import com.petsbnb.dto.PetFileDTO;

public interface IChatService {

	ChatDTO getToken(HashMap<Object, Object> req) throws Exception;

}
