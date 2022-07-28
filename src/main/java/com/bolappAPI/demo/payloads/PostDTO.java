package com.bolappAPI.demo.payloads;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.bolappAPI.demo.models.Category;
import com.bolappAPI.demo.models.Comment;
import com.bolappAPI.demo.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	
	
	private int postId;

	@NotEmpty
	private String title;
	
	private String imageName;
	
	private String content;
	
	private Date addedDate;
	
	private UserDTO user;
	private CategoryDTO category;
	
	private Set<CommentDTO> comments = new HashSet<>();

}
