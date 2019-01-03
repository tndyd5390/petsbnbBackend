package com.petsbnb.service;

import java.util.List;
import java.util.Map;

import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.dto.PetSitterFileDTO;

public interface IPetSitterServcie {
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception;
	public boolean insertPetSitterInfo(Map<String, Object> psMap, PetSitterDTO pDTO) throws Exception;
	public Map<Object, Object> getPetSitterInfoWithImage(String petSitterNo) throws Exception;
	public List<PetSitterFileDTO> insertAndGetPetSitterImage(PetSitterFileDTO pfDTO) throws Exception;
	public List<PetSitterFileDTO> deleteAndGetPetSitterImage(String petSitterNo, String petSitterFileNo) throws Exception;
	public boolean updatePetSitterInfo(PetSitterDTO p) throws Exception;
	public boolean updateTogglePetSitterReservationExposure(PetSitterDTO pDTO) throws Exception;
}
