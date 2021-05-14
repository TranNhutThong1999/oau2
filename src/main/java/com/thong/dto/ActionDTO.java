package com.thong.dto;


import com.thong.entity.ActionName;

public class ActionDTO {
	private ActionName name;
	private int idUser;
	private int idPost;
	public ActionName getName() {
		return name;
	}
	public void setName(ActionName name) {
		this.name = name;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

	
}
