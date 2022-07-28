package com.bolappAPI.demo.services;

import java.util.List;

import com.bolappAPI.demo.payloads.PostDTO;
import com.bolappAPI.demo.payloads.PostResponse;


public interface PostService {

	PostDTO createPost(PostDTO postDTO,int userId,int categoryId);

	PostDTO updatePost(PostDTO postDTO, int PostDTOId);

	public void deletePost(int postId);

	PostResponse getAllPost(int pageSize,int pageNumber,String sortBy,String sortDir);

	PostDTO PostById(int postId);
	
	List<PostDTO> getPostByCategory(int categoryId);

	List<PostDTO> getPostByUser(int userId);
	
	List<PostDTO> searchPosts(String keywords);
	
	
	
	

}
