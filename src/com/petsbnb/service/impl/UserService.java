package com.petsbnb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.dto.FileDTO;
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
		return userMapper.getUserLogin(uDTO);
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
}
