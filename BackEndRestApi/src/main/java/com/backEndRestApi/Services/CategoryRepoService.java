package com.backEndRestApi.Services;

import java.util.List;

import com.backEndRestApi.payloads.CategoryDto;

public interface CategoryRepoService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto CategoryDto,Integer categoryId);
	
	void deleteCategeory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getCategory();
	
	

}
