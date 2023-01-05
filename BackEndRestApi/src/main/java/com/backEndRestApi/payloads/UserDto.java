package com.backEndRestApi.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.backEndRestApi.Entities.Role;

public class UserDto {
	
	
	private int id;
	@NotEmpty
	//@NotNull
	private String name;
	@Email
	@NotEmpty
	//@Pattern(regexp="[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+")
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet();
	
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}

	
	
	
	
	
	

}
