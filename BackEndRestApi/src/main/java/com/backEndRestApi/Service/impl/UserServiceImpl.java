package com.backEndRestApi.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backEndRestApi.Config.AppConstant;
import com.backEndRestApi.Entities.Role;
import com.backEndRestApi.Entities.User;
import com.backEndRestApi.Exception.ResourceNotFoundException;
import com.backEndRestApi.Repository.RoleRepo;
import com.backEndRestApi.Repository.UserRepo;
import com.backEndRestApi.Services.UserService;
import com.backEndRestApi.payloads.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		// TODO Auto-generated method stub
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
	
		// TODO Auto-generated method stub
		return userDto1;
	}

	@Override
	public UserDto getByuserId(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		// TODO Auto-generated method stub
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userToDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return userToDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		// TODO Auto-generated method stub
		this.userRepo.delete(user);
	}
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.modelMapper.map(userDto, User.class);
		
		// Encode the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstant.ROLE_NORMAL).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

	
	public User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
//		User u=new User();
//		u.setId(userDto.getId());
//		System.out.println(u);
//		u.setName(userDto.getName());
//		System.out.println(u);
//		u.setEmail(userDto.getEmail());
//		u.setAbout(userDto.getAbout());
//		u.setPassword(userDto.getPassword());
		return user;
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userdto = this.modelMapper.map(user, UserDto.class);
//		UserDto userdto=new UserDto();
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setAbout(user.getAbout());
//		userdto.setPassword(user.getPassword());
		return userdto;
	}


}
