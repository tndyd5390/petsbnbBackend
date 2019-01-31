package com.petsbnb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.ReservationInfoDTO;
import com.petsbnb.dto.ReservationPetInfoDTO;
import com.petsbnb.persistance.mapper.ReservationMapper;
import com.petsbnb.service.IReservationService;

@Service("ReservationService")
public class ReservationService implements IReservationService {

	@Resource(name="ReservationMapper")
	private ReservationMapper reservationMapper;

	@Override
	public boolean insertReservationInfo(ReservationInfoDTO rDTO, List<String> petNoList) throws Exception {
		int insertReservation = reservationMapper.insertReservationInfo(rDTO);
		String reservationInfoNo = rDTO.getReservationInfoNo();
		
		Map<String, Object> reservationPetInfoMap = new HashMap<>();
		List<ReservationPetInfoDTO> rpDTOList = new ArrayList<>();
		for(String petNo : petNoList){
			ReservationPetInfoDTO rpDTO = new ReservationPetInfoDTO();
			System.out.println(reservationInfoNo);
			rpDTO.setReservationInfoNo(reservationInfoNo);
			rpDTO.setPetNo(petNo);
			rpDTO.setRegNo(rDTO.getServiceReceiver());
			rpDTOList.add(rpDTO);
		}
		
		reservationPetInfoMap.put("reservationPetInfo", rpDTOList);
		
		int insertReservationPet = reservationMapper.insertReservationPetInfo(reservationPetInfoMap);
		
		return insertReservation != 0 && insertReservationPet != 0;
	}

	@Override
	public String getServiceProviderToken(String serviceProvider) throws Exception {
		return reservationMapper.getServiceProviderToken(serviceProvider);
	}

	
}
