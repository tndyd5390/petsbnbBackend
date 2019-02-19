package com.petsbnb.service;

import java.util.List;
import java.util.Map;

import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.dto.PetSitterFileDTO;
import com.petsbnb.dto.ReservationInfoDTO;

public interface IPetSitterServcie {
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception;
	public boolean insertPetSitterInfo(Map<String, Object> psMap, PetSitterDTO pDTO) throws Exception;
	public Map<Object, Object> getPetSitterInfoWithImage(String petSitterNo) throws Exception;
	public List<PetSitterFileDTO> insertAndGetPetSitterImage(PetSitterFileDTO pfDTO) throws Exception;
	public List<PetSitterFileDTO> deleteAndGetPetSitterImage(String petSitterNo, String petSitterFileNo) throws Exception;
	public boolean updatePetSitterInfo(PetSitterDTO p) throws Exception;
	public boolean updateTogglePetSitterReservationExposure(PetSitterDTO pDTO) throws Exception;
	public int updateStartReservationExposure(PetSitterDTO pDTO) throws Exception;
	public int updateStopReservationExposure(PetSitterDTO pDTO) throws Exception;
	public PetSitterDTO getPDTO(String petSitterNo) throws Exception;
	public List<Map<String, Object>> getPetSitterReservationList(String userNo) throws Exception;
	public Map<Object, Object> getPetSitterReservationDetail(String reservationNo) throws Exception;
	public Map<String, Object> updateRejectReservation(String reservationNo) throws Exception;
	public Map<String, Object> updateApprovalReservation(String reservationNo) throws Exception;
	public Map<String, Object> updateProgressReservation(String reservationNo) throws Exception;
	public Map<String, Object> updateCompleteReservation(String reservationNo) throws Exception;
	public Map<Object, Object> getPetSitterPointInfo(String userNo) throws Exception;
	public Map<Object, Object> requestRefund(Map<String, Object> refundMap) throws Exception;
	public Map<Object, Object> getPointDetail(String pointInfoNo) throws Exception;
	public Map<Object, Object> getPetDetail(String petNo) throws Exception;
}
