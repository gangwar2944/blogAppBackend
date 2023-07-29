package com.blogApps.blogApp.payload;

import lombok.Getter;
import lombok.Setter;
@Setter 
@Getter
public class CommentDto {
	
    private Long id;
    private String content;
    
    
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(Long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
    
    
}
