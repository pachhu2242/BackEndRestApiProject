package com.backEndRestApi.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//1. get Token
		String requestToken = request.getHeader("Authorization");
		//Bearer 23564589722
		System.out.println(requestToken);
		
		String username = null;
		String token = null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);	
			}catch(IllegalArgumentException e) {
				System.out.println("Unable to get Token");	
			}catch(ExpiredJwtException e) {
				System.out.println("Jwt Token has Expired");	
			}catch(MalformedJwtException e) {
				System.out.println("Invalid JWT");	
			}
		}else {
			System.out.println("JWT token does not begin with Bearer");
		}
		
		//2. Validate Token -> Once we get the Token
		if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				//if everything is ok
				UsernamePasswordAuthenticationToken usernamepasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamepasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamepasswordAuthentication);
				
			}else {
				System.out.println("Invalid Jwt Token");
			}
			
		}else {
			System.out.println("User name is null or contecxt is not Null");
		}
		
		
		filterChain.doFilter(request, response);
		
	}
	
	

}
