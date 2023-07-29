package com.blogApps.blogApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
	@ManyToOne
	private Post post;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Comment(Long id, String content, Post post) {
		super();
		this.id = id;
		this.content = content;
		this.post = post;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	
	
}
