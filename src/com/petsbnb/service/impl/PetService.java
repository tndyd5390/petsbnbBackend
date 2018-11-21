package com.petsbnb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.PetDTO;
import com.petsbnb.dto.PetFileDTO;
import com.petsbnb.persistance.mapper.PetMapper;
import com.petsbnb.service.IPetService;

@Service("PetService")
public class PetService implements IPetService{

	@Resource(name="PetMapper")
	private PetMapper petMapper;

	@Override
	public boolean insertPetProfile(Map<String, Object> pfMap, PetDTO pDTO) throws Exception {
		
		int insertPetProfile = petMapper.insertPetProfile(pDTO);
		String petNo = pDTO.getPetNo();
		
		List<PetFileDTO> pList = (List<PetFileDTO>)pfMap.get("petImages");
		
		for(PetFileDTO pfDTO : pList){
			pfDTO.setPetNo(petNo);
		}
		
		pfMap.put("petImages", pList);
		
		int insertPetImages = petMapper.insertPetImageFile(pfMap);
		
		boolean result = false;
		
		if(insertPetProfile > 0 && insertPetImages > 0){
			result = true;
		}
		
		return false;
	}
}
