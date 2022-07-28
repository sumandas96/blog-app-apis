package com.bolappAPI.demo.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Filter as Required information

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDTO> content;
	private int pageSize;
	private int pagenumber;
	private long totalelements;
	private int totalpages;
	private boolean islastPage;
	

}
