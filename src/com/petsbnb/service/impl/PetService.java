package com.petsbnb.service.impl;

import java.util.HashMap;
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
		System.out.println("inserPetProfile : " + insertPetProfile);
		System.out.println("insertPetImages : " + insertPetImages);
		if(insertPetProfile > 0 && insertPetImages > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public List<PetDTO> getPetList(String userNo) throws Exception {
		return petMapper.getPetList(userNo);
	}

	@Override
	public Map<String, Object> getPetInfo(PetDTO pDTO) throws Exception {
		Map<String, Object> result = new HashMap<>();
		PetDTO pDTO1 = petMapper.getPetDTO(pDTO);
		List<PetFileDTO> pList = petMapper.getPetFileDTO(pDTO);
		result.put("petDTO", pDTO1);
		result.put("petFileList", pList);
		return result;
	}
}
