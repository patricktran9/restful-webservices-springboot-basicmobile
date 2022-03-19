package com.javabackend.mobileapp.webservicesmobile.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javabackend.mobileapp.webservicesmobile.Utils.Utils;
import com.javabackend.mobileapp.webservicesmobile.dto.UserDto;
import com.javabackend.mobileapp.webservicesmobile.entity.UserEntity;
import com.javabackend.mobileapp.webservicesmobile.exceptions.UserServiceExceptions;
import com.javabackend.mobileapp.webservicesmobile.model_response.ErrorMessages;
import com.javabackend.mobileapp.webservicesmobile.repository.UserRepository;
import com.javabackend.mobileapp.webservicesmobile.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto dto) {
		//check user already exists
		if(userRepository.findByEmail(dto.getEmail()) != null) throw new RuntimeException("Record already exists..");
		UserEntity userEntity= new UserEntity();
		BeanUtils.copyProperties(dto, userEntity);
		//encode password
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		//generate userid
		String publicUserId= utils.generatedUserId(20);
		userEntity.setUserId(publicUserId);
		UserEntity storedUserDetails= userRepository.save(userEntity);
		
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity= userRepository.findByEmail(email);
		if(userEntity ==null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity= userRepository.findByEmail(email);
		if(userEntity== null) throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto findUserByUserId(String UserId) {
		UserEntity userEntity= userRepository.findByUserId(UserId);
		if(userEntity== null) throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUserDetails(String userId, UserDto userDto) {
		UserEntity userEntity= userRepository.findByUserId(userId);
		if(userEntity == null) throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity updatedUser=userRepository.save(userEntity);
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity= userRepository.findByUserId(userId);
		if(userEntity == null) throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);
		
	}

}
