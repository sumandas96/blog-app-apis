package com.bolappAPI.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolappAPI.demo.exceptions.ResourceNotFoundException;
import com.bolappAPI.demo.models.Comment;
import com.bolappAPI.demo.models.Post;
import com.bolappAPI.demo.payloads.CommentDTO;
import com.bolappAPI.demo.payloads.PostResponse;
import com.bolappAPI.demo.repositiries.CommentRepo;
import com.bolappAPI.demo.repositiries.PostRepo;
import com.bolappAPI.demo.services.CommentService;


@Service
public class CommentServiceimpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, int postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "postid", postId));
		
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "commentid", commentId));
		 this.commentRepo.delete(comment);;
	}

}
