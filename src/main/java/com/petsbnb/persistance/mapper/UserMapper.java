package com.petsbnb.persistance.mapper;

import java.util.List;
import com.petsbnb.config.Mapper;
import com.petsbnb.dto.FileDTO;
import com.petsbnb.dto.PetSitterApplyDTO;
import com.petsbnb.dto.UserDTO;

@Mapper("UserMapper")
public interface UserMapper {
	public int insertUserReg(UserDTO uDTO) throws Exception;
	public UserDTO getUserLogin(UserDTO uDTO) throws Exception;
	public List<UserDTO> getEmailCheck(UserDTO uDTO) throws Exception;
	public UserDTO getCheckPassword(UserDTO uDTO) throws Exception;
	public UserDTO getUserInfo(UserDTO uDTO) throws Exception;
	public int updateUserPassword(UserDTO uDTO) throws Exception;
	public int insertUserImage(FileDTO fDTO) throws Exception;
	public int updateUserImage(FileDTO fDTO) throws Exception;
	public int updateUserAddress(UserDTO uDTO) throws Exception;
	public FileDTO getUserImage(UserDTO uDTO) throws Exception;
	public UserDTO getUserFindEmail(UserDTO uDTO) throws Exception;
	public UserDTO getUserFindPassword(UserDTO uDTO) throws Exception;
	public int updateUserTmpPassword(UserDTO uDTO) throws Exception;
	public UserDTO getCheckPetSitter(UserDTO uDTO) throws Exception;
	public UserDTO getUserAddress(UserDTO uDTO) throws Exception;
	public int insertPetSitterApply(PetSitterApplyDTO pDTO) throws Exception;
	public PetSitterApplyDTO checkAppliedUser(String userNo) throws Exception;
	public int updateDeviceToken(UserDTO uDTO) throws Exception;
}
