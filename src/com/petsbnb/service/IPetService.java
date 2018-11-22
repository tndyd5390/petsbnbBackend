package com.petsbnb.service;

import java.util.List;
import java.util.Map;

import com.petsbnb.dto.PetDTO;
import com.petsbnb.dto.PetFileDTO;

public interface IPetService {
	public boolean insertPetProfile(Map<String, Object> pfMap, PetDTO pDTO) throws Exception;
	public List<PetDTO> getPetList(String userNo) throws Exception;
	public Map<String, Object> getPetInfo(PetDTO pDTO) throws Exception;
}
