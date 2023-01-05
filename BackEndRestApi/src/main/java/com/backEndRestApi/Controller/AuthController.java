package com.backEndRestApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backEndRestApi.Exception.ApiException;
import com.backEndRestApi.Security.JwtAuthResponse;
import com.backEndRestApi.Security.JwtTokenHelper;
import com.backEndRestApi.Services.UserService;
import com.backEndRestApi.payloads.JwtAuthRequest;
import com.backEndRestApi.payloads.UserDto;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request
			) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails =this.userDetailService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
			JwtAuthResponse	response = new JwtAuthResponse();
			response.setToken(token);
			return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =	new UsernamePasswordAuthenticationToken(username, password);
		try {
		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("Inavalid credentials...");
			throw new ApiException("Invalid Username and password");
		}
		
	}
	//Register new user 
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		
		UserDto registerUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registerUser, HttpStatus.CREATED);
		
	}
	
}
