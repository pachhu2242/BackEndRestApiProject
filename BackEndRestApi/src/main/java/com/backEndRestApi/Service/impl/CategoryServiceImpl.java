package com.backEndRestApi.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEndRestApi.Entities.Category;
import com.backEndRestApi.Exception.ResourceNotFoundException;
import com.backEndRestApi.Repository.categoryRepo;
import com.backEndRestApi.Services.CategoryRepoService;
import com.backEndRestApi.payloads.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryRepoService {
	
	@Autowired
	private categoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat, CategoryDto.class) ;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto CategoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category",null,0));
		cat.setCategoryTitle(CategoryDto.getCategoryTitle());
		cat.setCategoryDescription(CategoryDto.getCategoryDescription());
		Category updateCategory = this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategeory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategory() {
		// TODO Auto-generated method stub
		List<Category> catogories=this.categoryRepo.findAll();
		List<CategoryDto> catDto = catogories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}
	
	

}
