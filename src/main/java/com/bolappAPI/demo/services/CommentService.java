package com.bolappAPI.demo.services;

import com.bolappAPI.demo.payloads.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentDTO,int postId);
	void  deleteComment(int commentId);

}
