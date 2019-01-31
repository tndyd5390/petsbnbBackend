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

	@Override
	public List<PetFileDTO> deleteImageAndGetImage(PetFileDTO pfDTO) throws Exception {
		int deleteRow = petMapper.deletePetFile(pfDTO);
		List<PetFileDTO> pfList = petMapper.getPetFileList(pfDTO);
		return pfList;
	}

	@Override
	public List<PetFileDTO> insertImageAndGetImage(PetFileDTO pfDTO) throws Exception {
		int insertRow = petMapper.insertPetFile(pfDTO);
		List<PetFileDTO> pfList = petMapper.getPetFileList(pfDTO);
		return pfList;
	}

	@Override
	public int updatePetProfile(PetDTO pDTO) throws Exception {
		return petMapper.updatePetProfile(pDTO);
	}

	@Override
	public boolean deletePetProfile(PetDTO pDTO) throws Exception {
		boolean result = false;
		int deletePetImage = petMapper.deletePetImage(pDTO);
		int deletePetProfile = petMapper.deletePetProfile(pDTO);
		if(deletePetImage != 0 && deletePetProfile != 0){
			result = true;
		}
		return result;
	}

	@Override
	public List<PetDTO> getSelectedPetList(Map<String, Object> selectedPetMap) throws Exception {
		return petMapper.getSelectedPetList(selectedPetMap);
	}

	@Override
	public List<PetDTO> getAvaliablePetList(Map<String, Object> avaliablePetKind) throws Exception {
		return petMapper.getAvaliablePetList(avaliablePetKind);
	}
}
