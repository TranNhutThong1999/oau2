package com.thong.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class District extends Common{
	private String name;
	
	@OneToMany(mappedBy = "district",cascade = CascadeType.ALL)
	private List<Address> address;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
