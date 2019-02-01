package com.petsbnb.persistance.mapper;

import java.util.List;
import java.util.Map;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.ReservationInfoDTO;

@Mapper("ReservationMapper")
public interface ReservationMapper {
	public int insertReservationInfo(ReservationInfoDTO rDTO) throws Exception;
	public int insertReservationPetInfo(Map<String, Object> reservationPetInfoMap) throws Exception;
	public String getServiceProviderToken(String serviceProvider) throws Exception;
	public List<Map<String, Object>> getReservationList(String serviceReceiver) throws Exception;
	public Map<String, Object> getReservationDetail(String reservationNo) throws Exception;
	public Map<String, Object> getImpUid(String reservationNo) throws Exception;
	public int updateReservationCancel(String reservationNo) throws Exception;
}
