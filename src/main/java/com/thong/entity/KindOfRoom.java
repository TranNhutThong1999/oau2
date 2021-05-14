package com.thong.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kindofroom")
public class KindOfRoom extends Common {
	private String name;

	@OneToMany(mappedBy = "kinhOfRoom", cascade = CascadeType.ALL)
	private List<Post> posts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
