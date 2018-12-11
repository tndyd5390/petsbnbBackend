package com.petsbnb.persistance.mapper;

import java.util.List;
import java.util.Map;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.dto.PetSitterFileDTO;

@Mapper("PetSitterMapper")
public interface PetSitterMapper {
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception;
	public int insertPetSitterInfo(PetSitterDTO pDTO) throws Exception;
	public int insertPetSitterImageFile(Map<String, Object> psMap) throws Exception;
	public PetSitterDTO getPetSitterInfoByPetSitterNo(String petSitterNo) throws Exception;
	public List<PetSitterFileDTO> getPetSitterImage(String petSitterNo) throws Exception;
	public int insertPetSitterImage(PetSitterFileDTO pfDTO) throws Exception;
	public int deletePetSitterImage(String petSitterFileNo) throws Exception;
	public int updatePetSitterInfo(PetSitterDTO p) throws Exception;
}
