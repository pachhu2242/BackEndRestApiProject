package com.backEndRestApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backEndRestApi.Entities.Category;
import com.backEndRestApi.Entities.Post;
import com.backEndRestApi.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String Title);

}
