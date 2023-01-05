package com.backEndRestApi.Services;

import java.util.List;

import com.backEndRestApi.Entities.Post;
import com.backEndRestApi.payloads.PostDto;
import com.backEndRestApi.payloads.PostResponse;

public interface PostService {
	
	//Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	//Update
	PostDto updatePost(PostDto postDto, Integer postId);
	//Delete
	void deletePost(Integer postId);
	//Get All
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	//Get
	PostDto getePostById(Integer postId);
	//Get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	//Get all post by User
	List<PostDto> getPostByUser(Integer userId);
	//Search Post
	List<PostDto> searchPosts(String keyword);
	

}
