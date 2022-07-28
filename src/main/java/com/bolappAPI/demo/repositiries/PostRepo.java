package com.bolappAPI.demo.repositiries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bolappAPI.demo.models.Category;
import com.bolappAPI.demo.models.Post;
import com.bolappAPI.demo.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user); 
	List<Post> findByCategory(Category  category); 
	
	@Query("select p from  Post p where p.title like :key")
	List<Post> findByTitle(@Param("key") String title); 

}
