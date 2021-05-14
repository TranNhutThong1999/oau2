package com.thong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "criteria")
public class Criteria extends Common{
	@Column(name = "pricestart")
	private double priceStart;
	
	@Column(name = "priceend")
	private double priceEnd;
	
	@Column(name = "acreagestart")
	private double acreageStart;
	
	@Column(name = "acreageend")
	private double acreageEnd;
	private boolean inform;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "kindOfRoom_id")
	private KindOfRoom kindOfRoom;
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;

	public double getPriceStart() {
		return priceStart;
	}

	public void setPriceStart(double priceStart) {
		this.priceStart = priceStart;
	}

	public double getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(double priceEnd) {
		this.priceEnd = priceEnd;
	}

	public double getAcreageStart() {
		return acreageStart;
	}

	public void setAcreageStart(double acreageStart) {
		this.acreageStart = acreageStart;
	}

	public double getAcreageEnd() {
		return acreageEnd;
	}

	public void setAcreageEnd(double acreageEnd) {
		this.acreageEnd = acreageEnd;
	}

	public boolean isInform() {
		return inform;
	}

	public void setInform(boolean inform) {
		this.inform = inform;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public KindOfRoom getKindOfRoom() {
		return kindOfRoom;
	}

	public void setKindOfRoom(KindOfRoom kindOfRoom) {
		this.kindOfRoom = kindOfRoom;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	
}
