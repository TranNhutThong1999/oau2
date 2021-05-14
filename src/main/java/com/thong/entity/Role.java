package com.thong.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "role")
public class Role extends Common {
	
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> list= new ArrayList<User>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [name=" + name +"]";
	}
	
	
}
