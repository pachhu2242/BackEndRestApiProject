package com.backEndRestApi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backEndRestApi.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
		Optional<User> findByEmail(String email);

}
