package com.javabackend.mobileapp.webservicesmobile.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.javabackend.mobileapp.webservicesmobile.dto.UserDto;

public interface UserService extends UserDetailsService{
 UserDto createUser(UserDto dto);
 UserDto getUser(String email);
 UserDto findUserByUserId(String id);
 UserDto updateUserDetails(String userId, UserDto userDto);
 void deleteUser(String userId);
}
