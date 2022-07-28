package com.bolappAPI.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiPanelUI;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolappAPI.demo.config.AppConstants;
import com.bolappAPI.demo.models.Post;
import com.bolappAPI.demo.payloads.ApiResponse;
import com.bolappAPI.demo.payloads.PostDTO;
import com.bolappAPI.demo.payloads.PostResponse;
import com.bolappAPI.demo.services.FileService;
import com.bolappAPI.demo.services.PostService;
import com.fasterxml.jackson.databind.ser.std.FileSerializer;

import lombok.val;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	String path;

	@PostMapping("/user/{userID}/category/{categoryID}/posts")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable("userID") int uid,
			@PathVariable("categoryID") int catId) {

		PostDTO createPost = this.postService.createPost(postDTO, uid, catId);

		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);

	}

	// upload image API

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile file,
			@PathVariable Integer postId) throws IOException {
		

		PostDTO postById = this.postService.PostById(postId); 
		/*we are putting this line here because in case the 
		id is not presenmt we will get Rsource not foun d exception 
		and rest of the code will not work*/

		
		String uploadImage = this.fileService.uploadImage(path, file);
		
		//PostDTO postById = this.postService.PostById(postId);
		postById.setImageName(uploadImage);
		
		PostDTO updatePost = this.postService.updatePost(postById, postId);

		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);

	}

	// get post by user

	@GetMapping("/user/{userID}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable int userID) {

		List<PostDTO> postByUser = this.postService.getPostByUser(userID);

		return new ResponseEntity<List<PostDTO>>(postByUser, HttpStatus.OK);

	}

	// get post by category

	@GetMapping("/category/{categoryID}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable int categoryID) {

		List<PostDTO> postByUser = this.postService.getPostByCategory(categoryID);

		return new ResponseEntity<List<PostDTO>>(postByUser, HttpStatus.OK);

	}

	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int size,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int number,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)

	// Annotation which indicates that a method parameter should be bound to a
	// webrequest parameter.
	{
		PostResponse allPost = this.postService.getAllPost(size, number, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}

	// get post by id

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostByID(@PathVariable int postId) {
		PostDTO postById = this.postService.PostById(postId);

		return new ResponseEntity<PostDTO>(postById, HttpStatus.OK);
	}
	
	
	//method to serve images
	@GetMapping(value="/posts/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable ("imageName") String imageName,
			HttpServletResponse httpServletResponse	) throws IOException
	{
		InputStream resource = this.fileService.getResource(path, imageName);
		httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, httpServletResponse.getOutputStream());
			
	}

	//
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postdto, @PathVariable int postId) {

		PostDTO postById = this.postService.updatePost(postdto, postId);

		return new ResponseEntity<PostDTO>(postById, HttpStatus.OK);

	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> delete(@PathVariable int postId) {
		this.postService.deletePost(postId);

		return new ResponseEntity(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	// search by post

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPost(@PathVariable("keywords") String keywords)

	{
		List<PostDTO> searchpost = this.postService.searchPosts(keywords);

		return new ResponseEntity<List<PostDTO>>(searchpost, HttpStatus.OK);

	}

}
