package com.bolappAPI.demo.payloads;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
	

	private int categoryId;
	
	@NotEmpty
	private String categoryTitle;
	
	@NotEmpty
	private String categoryDescription;

}
