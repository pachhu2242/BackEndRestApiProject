package com.backEndRestApi.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backEndRestApi.Services.CategoryRepoService;
import com.backEndRestApi.payloads.ApiResponse;
import com.backEndRestApi.payloads.CategoryDto;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepoService categoryService1;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto category =this.categoryService1.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto Updatecategory =this.categoryService1.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(Updatecategory, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> DeleteCategory(@PathVariable Integer catId){
		this.categoryService1.deleteCategeory(catId) ;
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully", false), HttpStatus.OK);
	}
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		CategoryDto category =this.categoryService1.getCategory(catId);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);	
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> category =this.categoryService1.getCategory();
		return ResponseEntity.ok(category);	
	}
	
	
	
	
}
