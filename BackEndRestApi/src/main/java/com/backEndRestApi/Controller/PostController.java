package com.backEndRestApi.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backEndRestApi.Config.AppConstant;
import com.backEndRestApi.Services.PostService;
import com.backEndRestApi.payloads.ApiResponse;
import com.backEndRestApi.payloads.PostDto;
import com.backEndRestApi.payloads.PostResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	
	@PostMapping("/users/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createtPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
	PostDto CreatedPostDto = this.postService.createPost(postDto, userId, categoryId);
	return new ResponseEntity<PostDto>(CreatedPostDto, HttpStatus.CREATED);	
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);	
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue=AppConstant.Page_Number ,required=false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue=AppConstant.Page_Size ,required=false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue=AppConstant.Sort_By ,required=false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue=AppConstant.Sort_Dir ,required=false) String sortDir
		
			){
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto = this.postService.getePostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post Is Deleted Successfully", true);	
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto upadatedPost =this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(upadatedPost, HttpStatus.OK);	
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keywords") String keyword){
		List<PostDto> result = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
		
	}
	
	
}


