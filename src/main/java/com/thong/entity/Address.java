package com.thong.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends Common {
	private String ward;
	private String subAddress;
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;
	
	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getSubAddress() {
		return subAddress;
	}

	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "Address [ward=" + ward + ", subAddress=" + subAddress + ", district=" + district + "]";
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
