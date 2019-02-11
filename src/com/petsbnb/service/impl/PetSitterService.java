package com.petsbnb.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.PetFileDTO;
import com.petsbnb.dto.PetSitterDTO;
import com.petsbnb.dto.PetSitterFileDTO;
import com.petsbnb.dto.ReservationInfoDTO;
import com.petsbnb.persistance.mapper.PetSitterMapper;
import com.petsbnb.service.IPetSitterServcie;
import com.petsbnb.util.AES256Util;
import com.petsbnb.util.CmmUtil;
import com.petsbnb.util.HttpUtil;

@Service("PetSitterService")
public class PetSitterService implements IPetSitterServcie {
	
	@Resource(name="PetSitterMapper")
	private PetSitterMapper petSitterMapper;

	@Override
	public PetSitterDTO getPetSitterInfo(String userNo) throws Exception {
		return petSitterMapper.getPetSitterInfo(userNo);
	}

	@Override
	public boolean insertPetSitterInfo(Map<String, Object> psMap, PetSitterDTO pDTO) throws Exception {
		int insertPetSitterInfo = petSitterMapper.insertPetSitterInfo(pDTO);
		String petSitterNo = pDTO.getPetSitterNo();
		
		List<PetSitterFileDTO> pList = (List<PetSitterFileDTO>)psMap.get("petSitterImages");
		
		for(PetSitterFileDTO pfDTO : pList){
			pfDTO.setPetSitterNo(petSitterNo);
		}
		
		psMap.put("petSitterImages", pList);
		
		int insertPetSitterImages = petSitterMapper.insertPetSitterImageFile(psMap);
		
		return insertPetSitterInfo > 0 && insertPetSitterImages > 0;
	}

	@Override
	public Map<Object, Object> getPetSitterInfoWithImage(String petSitterNo) throws Exception {
		Map<Object, Object> result = new HashMap<>();
		PetSitterDTO pDTO = petSitterMapper.getPetSitterInfoByPetSitterNo(petSitterNo);
		pDTO.setRefundAccountName(AES256Util.strDecode(pDTO.getRefundAccountName()));
		pDTO.setRefundBank(AES256Util.strDecode(pDTO.getRefundBank()));
		pDTO.setRefundAccountNumber(AES256Util.strDecode(pDTO.getRefundAccountNumber()));
		result.put("petSitterInfo", pDTO);
		result.put("petSitterImages", petSitterMapper.getPetSitterImage(petSitterNo));
		return result;
	}

	@Override
	public List<PetSitterFileDTO> insertAndGetPetSitterImage(PetSitterFileDTO pfDTO) throws Exception {
		int insertPetSitterImage = petSitterMapper.insertPetSitterImage(pfDTO);
		List<PetSitterFileDTO> pList = petSitterMapper.getPetSitterImage(pfDTO.getPetSitterNo());
		return pList;
	}

	@Override
	public List<PetSitterFileDTO> deleteAndGetPetSitterImage(String petSitterNo, String petSitterFileNo) throws Exception {
		int deletePetSitterImage = petSitterMapper.deletePetSitterImage(petSitterFileNo);
		List<PetSitterFileDTO> pList = petSitterMapper.getPetSitterImage(petSitterNo);
		return pList;
	}

	@Override
	public boolean updatePetSitterInfo(PetSitterDTO p) throws Exception {
		int update = petSitterMapper.updatePetSitterInfo(p);
		return update > 0;
	}

	@Override
	public boolean updateTogglePetSitterReservationExposure(PetSitterDTO pDTO) throws Exception {
		int result = petSitterMapper.updateTogglePetSitterReservationExposure(pDTO);
		return result > 0;
	}

	@Override
	public int updateStartReservationExposure(PetSitterDTO pDTO) throws Exception {
		return petSitterMapper.updateStartReservationExposure(pDTO);
	}

	@Override
	public int updateStopReservationExposure(PetSitterDTO pDTO) throws Exception {
		return petSitterMapper.updateStopReservationExposure(pDTO);
	}

	@Override
	public PetSitterDTO getPDTO(String petSitterNo) throws Exception {
		return petSitterMapper.getPDTO(petSitterNo);
	}

	@Override
	public List<Map<String, Object>> getPetSitterReservationList(String userNo) throws Exception {
		List<Map<String, Object>> reservationList = petSitterMapper.getPetSitterReservationList(userNo);
		for(Map<String, Object> reservation : reservationList) {
			reservation.put("userName", AES256Util.strDecode((String)reservation.get("userName")));
		}
		return reservationList;
	}

	@Override
	public Map<Object, Object> getPetSitterReservationDetail(String reservationNo) throws Exception {
		Map<String, Object> reservationDetail = petSitterMapper.getPetSitterReservationDetail(reservationNo);
		reservationDetail.put("userName", AES256Util.strDecode((String)reservationDetail.get("userName")));
		reservationDetail.put("userPhone", AES256Util.strDecode((String)reservationDetail.get("userPhone")));
		
		List<Map<String, Object>> reservationPetDetail = petSitterMapper.getPetSitterReservationPetDetail(reservationNo);
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("reservationDetail", reservationDetail);
		resultMap.put("reservationPetDetail", reservationPetDetail);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> updateRejectReservation(String reservationNo) throws Exception {
		petSitterMapper.updateRejectReservation(reservationNo);
		Map<String, Object> reservationDetail = petSitterMapper.getPetSitterReservationDetail(reservationNo);
		reservationDetail.put("userName", AES256Util.strDecode((String)reservationDetail.get("userName")));
		reservationDetail.put("userPhone", AES256Util.strDecode((String)reservationDetail.get("userPhone")));
		
		HttpUtil.cancelIamport((String)reservationDetail.get("imp_uid"), "예약 반려");
		
		Map<String, Object> serviceProviderName = petSitterMapper.getServiceProviderName(reservationNo);
		serviceProviderName.put("userName", AES256Util.strDecode((String)serviceProviderName.get("userName")));
		HttpUtil.sendFcm("펫시팅이 반려되었습니다.", ((String)serviceProviderName.get("userName") + "님에게 요청한 펫시팅이 반려되었습니다."), (String)reservationDetail.get("deviceToken"));
		return reservationDetail;
	}

	@Override
	public Map<String, Object> updateApprovalReservation(String reservationNo) throws Exception {
		petSitterMapper.updateApprovalReservation(reservationNo);
		Map<String, Object> reservationDetail = petSitterMapper.getPetSitterReservationDetail(reservationNo);
		reservationDetail.put("userName", AES256Util.strDecode((String)reservationDetail.get("userName")));
		reservationDetail.put("userPhone", AES256Util.strDecode((String)reservationDetail.get("userPhone")));
		Map<String, Object> serviceProviderName = petSitterMapper.getServiceProviderName(reservationNo);
		serviceProviderName.put("userName", AES256Util.strDecode((String)serviceProviderName.get("userName")));
		HttpUtil.sendFcm("예약이 승인되었습니다.", ((String)serviceProviderName.get("userName") + "님에게 요청한 펫시팅이 수락되었습니다."), (String)reservationDetail.get("deviceToken"));
		return reservationDetail;
	}

	@Override
	public Map<String, Object> updateProgressReservation(String reservationNo) throws Exception {
		petSitterMapper.updateProgressReservation(reservationNo);
		Map<String, Object> reservationDetail = petSitterMapper.getPetSitterReservationDetail(reservationNo);
		reservationDetail.put("userName", AES256Util.strDecode((String)reservationDetail.get("userName")));
		reservationDetail.put("userPhone", AES256Util.strDecode((String)reservationDetail.get("userPhone")));
		Map<String, Object> serviceProviderName = petSitterMapper.getServiceProviderName(reservationNo);
		serviceProviderName.put("userName", AES256Util.strDecode((String)serviceProviderName.get("userName")));
		HttpUtil.sendFcm("펫시팅이 시작되었습니다.", ((String)serviceProviderName.get("userName") + "님에게 요청한 펫시팅이 시작되었습니다."), (String)reservationDetail.get("deviceToken"));
		return reservationDetail;
	}

	@Override
	public Map<String, Object> updateCompleteReservation(String reservationNo) throws Exception {
		petSitterMapper.updateCompleteReservation(reservationNo);
		Map<String, Object> reservationDetail = petSitterMapper.getPetSitterReservationDetail(reservationNo);
		reservationDetail.put("userName", AES256Util.strDecode((String)reservationDetail.get("userName")));
		reservationDetail.put("userPhone", AES256Util.strDecode((String)reservationDetail.get("userPhone")));
		Map<String, Object> serviceProviderName = petSitterMapper.getServiceProviderName(reservationNo);
		serviceProviderName.put("userName", AES256Util.strDecode((String)serviceProviderName.get("userName")));
		HttpUtil.sendFcm("펫시팅이 완료되었습니다.", ((String)serviceProviderName.get("userName") + "님에게 요청한 펫시팅이 완료되었습니다."), (String)reservationDetail.get("deviceToken"));
		return reservationDetail;
	}
}
