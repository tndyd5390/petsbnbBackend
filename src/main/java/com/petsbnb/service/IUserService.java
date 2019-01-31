package com.petsbnb.service;

import java.util.List;
import java.util.Map;

import com.petsbnb.dto.FileDTO;
import com.petsbnb.dto.PetSitterApplyDTO;
import com.petsbnb.dto.UserDTO;

public interface IUserService {
	public UserDTO insertUserReg(UserDTO uDTO) throws Exception;
	public UserDTO getUserLogin(UserDTO uDTO) throws Exception;
	public List<UserDTO> getEmailCheck(UserDTO uDTO) throws Exception;
	public UserDTO getCheckPassword(UserDTO uDTO) throws Exception;
	public UserDTO getUserInfo(UserDTO uDTO) throws Exception;
	public int updateUserPassword(UserDTO uDTO) throws Exception;
	public boolean updateUserImage(FileDTO fDTO) throws Exception;
	public int updateUserAddress(UserDTO uDTO) throws Exception;
	public FileDTO getUserImage(UserDTO uDTO) throws Exception;
	public UserDTO getUserFindEmail(UserDTO uDTO) throws Exception;
	public UserDTO getUserFindPassword(UserDTO uDTO) throws Exception;
	public UserDTO getCheckPetSitter(UserDTO uDTO) throws Exception;
	public UserDTO getUserAddress(UserDTO uDTO) throws Exception;
	public int insertPetSitterApply(PetSitterApplyDTO pDTO) throws Exception;
	public PetSitterApplyDTO checkAppliedUser(String userNo) throws Exception;
}
