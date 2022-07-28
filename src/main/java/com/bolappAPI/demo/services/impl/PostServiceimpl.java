package com.bolappAPI.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bolappAPI.demo.exceptions.ResourceNotFoundException;
import com.bolappAPI.demo.models.Post;
import com.bolappAPI.demo.models.User;
import com.bolappAPI.demo.models.Category;
import com.bolappAPI.demo.payloads.PostDTO;
import com.bolappAPI.demo.payloads.PostResponse;
import com.bolappAPI.demo.repositiries.CategoryRepo;
import com.bolappAPI.demo.repositiries.PostRepo;
import com.bolappAPI.demo.repositiries.UserRepo;
import com.bolappAPI.demo.services.PostService;

@Service
public class PostServiceimpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDTO createPost(PostDTO postDTO, int userId, int categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category ID", categoryId));

		Post post = this.modelMapper.map(postDTO, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDTO.class);

	}

	@Override
	public PostDTO updatePost(PostDTO postdto, int postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post ID", postId));

		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());

		Post updatedpost = this.postRepo.save(post);
		PostDTO updatedpostdto = this.modelMapper.map(updatedpost, PostDTO.class);

		return updatedpostdto;
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post ID", postId));

		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(int pageSize, int pageNumber, String sortBy, String sortDir) {
		// TODO Auto-generated method
		Sort sort = null;

		if (sortDir.equalsIgnoreCase("asc")) {
			sort = sort.by(sortBy).ascending();
		} else
			sort = sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepo.findAll(pageable);

		List<Post> posts = pagepost.getContent();

		List<PostDTO> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postdtos);
		postResponse.setPageSize(pageSize);
		postResponse.setPagenumber(pageNumber);
		postResponse.setTotalelements(pagepost.getTotalElements());
		postResponse.setTotalpages(pagepost.getTotalPages());
		postResponse.setIslastPage(pagepost.isLast());

		return postResponse;
	}

	@Override
	public PostDTO PostById(int postId) {
		// TODO Auto-generated method stub

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post ID", postId));
//		post.stream().map((post) -> 
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(int categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category ID", categoryId));

		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDTO> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDTO> getPostByUser(int userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));

		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDTO> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDTO> searchPosts(String keywords) {
		// TODO Auto-generated method stub
		List<Post> findByTitle = this.postRepo.findByTitle("%"+keywords+"%");

		List<PostDTO> postdto = findByTitle.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return postdto;
	}

}
