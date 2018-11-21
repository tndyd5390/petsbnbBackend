package com.petsbnb.persistance.mapper;

import java.util.List;

import com.petsbnb.config.Mapper;
import com.petsbnb.dto.FileDTO;
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
}
