package com.petsbnb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.PetSitterDTO;
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
}
