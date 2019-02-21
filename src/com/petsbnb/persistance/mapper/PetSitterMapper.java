package com.petsbnb.persistance.mapper;

import java.util.List;
import java.util.Map;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.dto.PetSitterFileDTO;

@Mapper("PetSitterMapper")
public interface PetSitterMapper {
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception;
	public int insertPetSitterInfo(PetSitterDTO pDTO) throws Exception;
	public int insertPetSitterImageFile(Map<String, Object> psMap) throws Exception;
	public PetSitterDTO getPetSitterInfoByPetSitterNo(String petSitterNo) throws Exception;
	public List<PetSitterFileDTO> getPetSitterImage(String petSitterNo) throws Exception;
	public int insertPetSitterImage(PetSitterFileDTO pfDTO) throws Exception;
	public int deletePetSitterImage(String petSitterFileNo) throws Exception;
	public int updatePetSitterInfo(PetSitterDTO p) throws Exception;
	public int updateTogglePetSitterReservationExposure(PetSitterDTO pDTO) throws Exception;
	public int updateStartReservationExposure(PetSitterDTO pDTO) throws Exception;
	public int updateStopReservationExposure(PetSitterDTO pDTO) throws Exception;
	public PetSitterDTO getPDTO(String petSitterNo) throws Exception;
	public List<Map<String, Object>> getPetSitterReservationList(String userNo) throws Exception;
	public Map<String, Object> getPetSitterReservationDetail(String reservationNo) throws Exception;
	public List<Map<String, Object>> getPetSitterReservationPetDetail(String reservationNo) throws Exception;
	public int updateRejectReservation(String reservationNo) throws Exception;
	public int updateApprovalReservation(String reservationNo) throws Exception;
	public int updateProgressReservation(String reservationNo) throws Exception;
	public int updateCompleteReservation(String reservationNo) throws Exception;
	public Map<String, Object> getServiceProviderName(String reservationNo) throws Exception;
	public int insertPoint(Map<Object, Object> params) throws Exception;
	public Map<String, Object> getReserveRate() throws Exception;
	public List<Map<String, Object>> getPetSitterGetPoint(String userNo) throws Exception;
	public List<Map<String, Object>> getPetSitterRefundPoint(String userNo) throws Exception;
	public Map<String, Object> getPetSitterTotalPoint(String userNo) throws Exception;
	public Map<String, Object> getUserImage(String userNo) throws Exception;
	public int insertRequestRefund(Map<String, Object> refundMap) throws Exception;
	public Map<String, Object> getReservationInfoNoFromPointInfo(String pointInfoNo) throws Exception;
	public Map<String, Object> getPetDetail(String petNo) throws Exception;
	public List<Map<String, Object>> getPetImages(String petNo) throws Exception;
}
