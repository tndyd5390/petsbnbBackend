package com.petsbnb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.persistance.mapper.PetMapper;
import com.petsbnb.service.IPetService;

@Service("PetService")
public class PetService implements IPetService{

	@Resource(name="PetMapper")
	private PetMapper petMapper;
}
