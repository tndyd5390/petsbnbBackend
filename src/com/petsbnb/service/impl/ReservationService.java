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
import com.petsbnb.util.AES256Util;
import com.petsbnb.util.HttpUtil;

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

	@Override
	public List<Map<String, Object>> getReservationList(String serviceReceiver) throws Exception {
		List<Map<String, Object>> reservationList = reservationMapper.getReservationList(serviceReceiver);
		if(reservationList != null){
			for(Map<String, Object> reservation : reservationList){
				
				//reservationList데이터를 react native에서 목록으로 변환시키는데 id property가 문자열이여라 에러가 안나서 문자열로 바꿔줌
				reservation.put("id", reservation.get("id") + "");
				reservation.put("sitter", AES256Util.strDecode((String)reservation.get("sitter")));
			}
		}
		
		return reservationList;
	}

	@Override
	public Map<String, Object> getReservationDetatil(String reservationNo) throws Exception {
		return reservationMapper.getReservationDetail(reservationNo);
	}

	@Override
	public Map<String, Object> updateCancelReservation(String reservationNo, String reason) throws Exception {
		
		Map<String, Object> impUidMap = reservationMapper.getImpUid(reservationNo);
		String imp_uid = (String)impUidMap.get("imp_uid");
		boolean cancel = HttpUtil.cancelIamport(imp_uid, reason);
		int update = 0;
		if(cancel){
			update = reservationMapper.updateReservationCancel(reservationNo); 
		}
		return reservationMapper.getReservationDetail(reservationNo);
	}

	
}
