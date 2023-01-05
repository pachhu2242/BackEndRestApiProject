package com.backEndRestApi.Service.impl;

import java.util.Date;
//import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backEndRestApi.Entities.Category;
import com.backEndRestApi.Entities.Post;
import com.backEndRestApi.Entities.User;
import com.backEndRestApi.Exception.ResourceNotFoundException;
import com.backEndRestApi.Repository.PostRepo;
import com.backEndRestApi.Repository.UserRepo;
import com.backEndRestApi.Repository.categoryRepo;
import com.backEndRestApi.Services.PostService;
import com.backEndRestApi.payloads.PostDto;
import com.backEndRestApi.payloads.PostResponse;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private categoryRepo categoryRepoo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId", userId));
		
		Category category = this.categoryRepoo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class) ;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post upodatedpost = this.postRepo.save(post);
		return this.modelMapper.map(upodatedpost, PostDto.class) ;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
//		int pageSize = 5;
//		int pageNumber = 2;
		
		
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sorting = Sort.by(sortBy).ascending();
//			
//		}else {
//			sorting = Sort.by(sortBy).descending();
//		}
		
		Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
	
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPost = pagePost.getContent();
				
		List<PostDto> postDtos = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getePostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		System.out.println(categoryId);
		Category cat = this.categoryRepoo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDto = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
	List<Post> posts =	this.postRepo.findByTitleContaining(keyword);
	List<PostDto> postDto = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());	
	return postDto;
		
	}
	
	

	
}
