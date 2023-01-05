package com.backEndRestApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backEndRestApi.Entities.Category;

public interface categoryRepo extends JpaRepository<Category, Integer>{

}
