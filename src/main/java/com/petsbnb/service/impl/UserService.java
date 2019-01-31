package com.petsbnb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.FileDTO;
import com.petsbnb.dto.PetSitterApplyDTO;
import com.petsbnb.dto.UserDTO;
import com.petsbnb.persistance.mapper.UserMapper;
import com.petsbnb.service.IUserService;

@Service("UserService")
public class UserService implements IUserService {
	
	@Resource(name="UserMapper")
	private UserMapper userMapper;
	
	@Override
	public UserDTO insertUserReg(UserDTO uDTO) throws Exception {
		userMapper.insertUserReg(uDTO);
		return uDTO;
	}

	@Override
	public UserDTO getUserLogin(UserDTO uDTO) throws Exception {
		UserDTO userDTO = userMapper.getUserLogin(uDTO);
		if(userDTO != null){
			userMapper.updateDeviceToken(uDTO);
		}
		return userDTO;
	}

	@Override
	public List<UserDTO> getEmailCheck(UserDTO uDTO) throws Exception {
		return userMapper.getEmailCheck(uDTO);
	}

	@Override
	public UserDTO getCheckPassword(UserDTO uDTO) throws Exception {
		return userMapper.getCheckPassword(uDTO);
	}

	@Override
	public UserDTO getUserInfo(UserDTO uDTO) throws Exception {
		return userMapper.getUserInfo(uDTO);
	}

	@Override
	public int updateUserPassword(UserDTO uDTO) throws Exception {
		return userMapper.updateUserPassword(uDTO);
	}

	@Override
	public boolean updateUserImage(FileDTO fDTO) throws Exception {
		int num1 = userMapper.insertUserImage(fDTO);
		int num2 = userMapper.updateUserImage(fDTO);
		if(num1 != 0 &&num2 != 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int updateUserAddress(UserDTO uDTO) throws Exception {
		return userMapper.updateUserAddress(uDTO);
	}

	@Override
	public FileDTO getUserImage(UserDTO uDTO) throws Exception {
		return userMapper.getUserImage(uDTO);
	}

	@Override
	public UserDTO getUserFindEmail(UserDTO uDTO) throws Exception {
		return userMapper.getUserFindEmail(uDTO);
	}

	@Override
	public UserDTO getUserFindPassword(UserDTO uDTO) throws Exception {
		int updateTmpPass = userMapper.updateUserTmpPassword(uDTO);
		return userMapper.getUserFindPassword(uDTO);
	}

	@Override
	public UserDTO getCheckPetSitter(UserDTO uDTO) throws Exception {
		return userMapper.getCheckPetSitter(uDTO);
	}

	@Override
	public UserDTO getUserAddress(UserDTO uDTO) throws Exception {
		return userMapper.getUserAddress(uDTO);
	}

	@Override
	public int insertPetSitterApply(PetSitterApplyDTO pDTO) throws Exception {
		return userMapper.insertPetSitterApply(pDTO);
	}

	@Override
	public PetSitterApplyDTO checkAppliedUser(String userNo) throws Exception {
		return userMapper.checkAppliedUser(userNo);
	}
}
