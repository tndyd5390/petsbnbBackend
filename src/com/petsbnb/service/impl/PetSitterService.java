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
import com.petsbnb.util.AES256Util;
import com.petsbnb.util.CmmUtil;

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
		PetSitterDTO pDTO = petSitterMapper.getPetSitterInfoByPetSitterNo(petSitterNo);
		pDTO.setRefundAccountName(AES256Util.strDecode(pDTO.getRefundAccountName()));
		pDTO.setRefundBank(AES256Util.strDecode(pDTO.getRefundBank()));
		pDTO.setRefundAccountNumber(AES256Util.strDecode(pDTO.getRefundAccountNumber()));
		result.put("petSitterInfo", pDTO);
		result.put("petSitterImages", petSitterMapper.getPetSitterImage(petSitterNo));
		return result;
	}

	@Override
	public List<PetSitterFileDTO> insertAndGetPetSitterImage(PetSitterFileDTO pfDTO) throws Exception {
		int insertPetSitterImage = petSitterMapper.insertPetSitterImage(pfDTO);
		List<PetSitterFileDTO> pList = petSitterMapper.getPetSitterImage(pfDTO.getPetSitterNo());
		return pList;
	}

	@Override
	public List<PetSitterFileDTO> deleteAndGetPetSitterImage(String petSitterNo, String petSitterFileNo) throws Exception {
		int deletePetSitterImage = petSitterMapper.deletePetSitterImage(petSitterFileNo);
		List<PetSitterFileDTO> pList = petSitterMapper.getPetSitterImage(petSitterNo);
		return pList;
	}

	@Override
	public boolean updatePetSitterInfo(PetSitterDTO p) throws Exception {
		int update = petSitterMapper.updatePetSitterInfo(p);
		return update > 0;
	}

	@Override
	public boolean updateTogglePetSitterReservationExposure(PetSitterDTO pDTO) throws Exception {
		int result = petSitterMapper.updateTogglePetSitterReservationExposure(pDTO);
		return result > 0;
	}
}
