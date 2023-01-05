package com.backEndRestApi;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.backEndRestApi.Config.AppConstant;
import com.backEndRestApi.Entities.Role;
import com.backEndRestApi.Repository.RoleRepo;

@SpringBootApplication
public class BackEndRestApiApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BackEndRestApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(this.passwordEncoder.encode("admin"));
		
		try {
			
			Role role = new Role();
			role.setId(AppConstant.ROLE_ADMIN);
			role.setName("ROLE_ADMIN");
			
			
			Role role1 = new Role();
			role1.setId(AppConstant.ROLE_NORMAL);
			role1.setName("ROLE_NORMAL");
			
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			roles.add(role1);
			
			List<Role> result =  this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}