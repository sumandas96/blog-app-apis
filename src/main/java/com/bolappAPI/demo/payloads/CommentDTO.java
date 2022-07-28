package com.bolappAPI.demo.payloads;

import javax.annotation.Generated;
import javax.persistence.ManyToOne;

import com.bolappAPI.demo.models.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
	
	private int id;
	
	private String content;

	
}
