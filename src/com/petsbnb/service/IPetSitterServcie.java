package com.petsbnb.service;

import java.util.Map;

import com.petsbnb.dto.PetSitterDTO;

public interface IPetSitterServcie {
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception;
	public boolean insertPetSitterInfo(Map<String, Object> psMap, PetSitterDTO pDTO) throws Exception;
	public Map<Object, Object> getPetSitterInfoWithImage(String petSitterNo) throws Exception;
}
