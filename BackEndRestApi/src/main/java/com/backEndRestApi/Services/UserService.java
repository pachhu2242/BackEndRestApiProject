package com.backEndRestApi.Services;

import java.util.List;

import com.backEndRestApi.Entities.User;
import com.backEndRestApi.payloads.UserDto;

//import antlr.collections.List;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getByuserId(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);	
	
	//Registsreation
	UserDto registerNewUser(UserDto userDto);
	
	

}
