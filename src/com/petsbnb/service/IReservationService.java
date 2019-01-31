package com.petsbnb.service;

import java.util.List;

import com.petsbnb.dto.ReservationInfoDTO;
import com.petsbnb.dto.ReservationPetInfoDTO;

public interface IReservationService {
	
	public boolean insertReservationInfo(ReservationInfoDTO rDTO, List<String> petNoList) throws Exception;
	public String getServiceProviderToken(String serviceProvider) throws Exception;

}
