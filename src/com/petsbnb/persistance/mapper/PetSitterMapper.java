package com.petsbnb.persistance.mapper;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.PetSitterDTO;

@Mapper("PetSitterMapper")
public interface PetSitterMapper {
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception;
}
