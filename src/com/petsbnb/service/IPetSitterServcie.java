package com.petsbnb.service;

import com.petsbnb.dto.PetSitterDTO;

public interface IPetSitterServcie {
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception;
}
