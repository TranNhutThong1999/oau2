package com.thong.dto;


public class CommentDTO extends CommonDTO {
	private String content;
	private UserDTO user;
	private int idPost;
	
	public CommentDTO() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

}
