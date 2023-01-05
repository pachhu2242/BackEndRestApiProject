package com.backEndRestApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backEndRestApi.Entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	

}
