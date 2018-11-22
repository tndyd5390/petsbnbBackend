package com.petsbnb.persistance.mapper;

import java.util.List;
import java.util.Map;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.PetDTO;
import com.petsbnb.dto.PetFileDTO;

@Mapper("PetMapper")
public interface PetMapper {
	public int insertPetProfile(PetDTO pDTO) throws Exception;
	public int insertPetImageFile(Map<String, Object> pfMap) throws Exception;
	public List<PetDTO> getPetList(String userNo) throws Exception;
	public PetDTO getPetDTO(PetDTO pDTO) throws Exception;
	public List<PetFileDTO> getPetFileDTO(PetDTO pDTO) throws Exception;
}
