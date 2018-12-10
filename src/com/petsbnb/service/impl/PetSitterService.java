package com.petsbnb.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.PetFileDTO;
import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.dto.PetSitterFileDTO;
import com.petsbnb.persistance.mapper.PetSitterMapper;
import com.petsbnb.service.IPetSitterServcie;

@Service("PetSitterService")
public class PetSitterService implements IPetSitterServcie {
	
	@Resource(name="PetSitterMapper")
	private PetSitterMapper petSitterMapper;

	@Override
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception {
		return petSitterMapper.getPetSitterInfo(userNo);
	}

	@Override
	public boolean insertPetSitterInfo(Map<String, Object> psMap, PetSitterDTO pDTO) throws Exception {
		int insertPetSitterInfo = petSitterMapper.insertPetSitterInfo(pDTO);
		String petSitterNo = pDTO.getPetSitterNo();
		
		List<PetSitterFileDTO> pList = (List<PetSitterFileDTO>)psMap.get("petSitterImages");
		
		for(PetSitterFileDTO pfDTO : pList){
			pfDTO.setPetSitterNo(petSitterNo);
		}
		
		psMap.put("petSitterImages", pList);
		
		int insertPetSitterImages = petSitterMapper.insertPetSitterImageFile(psMap);
		
		return insertPetSitterInfo > 0 && insertPetSitterImages > 0;
	}

	@Override
	public Map<Object, Object> getPetSitterInfoWithImage(String petSitterNo) throws Exception {
		Map<Object, Object> result = new HashMap<>();
		result.put("petSitterInfo", petSitterMapper.getPetSitterInfoByPetSitterNo(petSitterNo));
		result.put("petSitterImages", petSitterMapper.getPetSitterImage(petSitterNo));
		return result;
	}
}
