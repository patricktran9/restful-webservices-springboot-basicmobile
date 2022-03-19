package com.javabackend.mobileapp.webservicesmobile.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import com.javabackend.mobileapp.webservicesmobile.dto.UserDto;
import com.javabackend.mobileapp.webservicesmobile.exceptions.UserServiceExceptions;
import com.javabackend.mobileapp.webservicesmobile.model.UserDetailsRequestModel;
import com.javabackend.mobileapp.webservicesmobile.model_response.ErrorMessages;
import com.javabackend.mobileapp.webservicesmobile.model_response.OperationStatusModel;
import com.javabackend.mobileapp.webservicesmobile.model_response.RequestOperationName;
import com.javabackend.mobileapp.webservicesmobile.model_response.RequestOperationStatus;
import com.javabackend.mobileapp.webservicesmobile.model_response.UserRest;
import com.javabackend.mobileapp.webservicesmobile.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserRest getUser(@PathVariable String id) {
		UserRest userResp= new UserRest();
		UserDto userDto=userService.findUserByUserId(id);
		BeanUtils.copyProperties(userDto, userResp);
		return userResp;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		UserDto userDto= new UserDto();
		if(userDetails.getFirstName().isEmpty()) throw new UserServiceExceptions(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUserResp= userService.createUser(userDto);
		UserRest userResp= new UserRest();
		BeanUtils.copyProperties(createdUserResp, userResp);
		return userResp;
	}
	@PutMapping(path = "/{id}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserDto userDto= new UserDto();
		if(userDetails.getFirstName().isEmpty()) throw new UserServiceExceptions(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto updatedUser=userService.updateUserDetails(id,userDto);
		UserRest returnUser= new UserRest();
		BeanUtils.copyProperties(updatedUser, returnUser);
		return returnUser;
	}
	
	@DeleteMapping(path ="/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel operationStatusModel= new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		userService.deleteUser(id);
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
	}
}
