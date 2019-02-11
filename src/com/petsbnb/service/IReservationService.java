package com.petsbnb.service;

import java.util.List;
import java.util.Map;
import com.petsbnb.dto.ReservationInfoDTO;

public interface IReservationService {
	
	public boolean insertReservationInfo(ReservationInfoDTO rDTO, List<String> petNoList) throws Exception;
	public String getServiceProviderToken(String serviceProvider) throws Exception;
	public List<Map<String, Object>> getReservationList(String serviceReceiver) throws Exception;
	public Map<String, Object> getReservationDetatil(String reservationNo) throws Exception;
	public Map<String, Object> updateCancelReservation(String reservationNo, String reason) throws Exception;
}
