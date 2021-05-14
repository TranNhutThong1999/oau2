package com.thong.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CommonDTO {
	private int id;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createdDate;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String createdBy;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date modifiedDate;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String modifedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifedBy() {
		return modifedBy;
	}

	public void setModifedBy(String modifedBy) {
		this.modifedBy = modifedBy;
	}
	
	
}
