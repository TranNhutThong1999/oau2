package com.thong.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post extends Common {
	private double price;
	private String title;
	private String content;
	
	@Enumerated(EnumType.STRING)
	private StatePost state;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "post")
	private Address address;
	
	private String phone;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "post")
	private DetailPost detail;
	
	@ManyToOne
	@JoinColumn(name = "kindofroom_id")
	private KindOfRoom kinhOfRoom;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
	private List<Image> image;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "post",fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "post",fetch = FetchType.LAZY)
	private List<Notification> notification;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Action> actions;
	
	public Post() {
		super();
	}

	public Post(String title, String content, double price, StatePost state, Address address, String phone,
			DetailPost detail, User user) {
		super();
		this.title = title;
		this.content = content;
		this.price = price;
		this.state = state;
		this.address = address;
		this.phone = phone;
		this.detail = detail;
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public StatePost getState() {
		return state;
	}

	public void setState(StatePost state) {
		this.state = state;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public DetailPost getDetail() {
		return detail;
	}

	public void setDetail(DetailPost detail) {
		this.detail = detail;
	}

	public KindOfRoom getKinhOfRoom() {
		return kinhOfRoom;
	}

	public void setKinhOfRoom(KindOfRoom kinhOfRoom) {
		this.kinhOfRoom = kinhOfRoom;
	}

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Notification> getNotification() {
		return notification;
	}

	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
}
