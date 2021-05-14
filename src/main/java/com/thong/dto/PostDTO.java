package com.thong.dto;

import java.util.List;

import com.thong.entity.Address;
import com.thong.entity.DetailPost;
import com.thong.entity.KindOfRoom;
import com.thong.entity.StatePost;

public class PostDTO extends CommonDTO {
	private String title;
	private String content;
	private double price;
	private StatePost state;
	private String phone;
	private AddressDTO address;
	private DetailPostDTO detail;
	private KindOfRoomDTO kinhOfRoom;
	private List<ImageDTO> image;
	private List<CommentDTO> comments;
	private UserDTO user;
	public PostDTO() {
		super();
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public DetailPostDTO getDetail() {
		return detail;
	}
	public void setDetail(DetailPostDTO detail) {
		this.detail = detail;
	}
	public KindOfRoomDTO getKinhOfRoom() {
		return kinhOfRoom;
	}
	public void setKinhOfRoom(KindOfRoomDTO kinhOfRoom) {
		this.kinhOfRoom = kinhOfRoom;
	}
	
	public List<ImageDTO> getImage() {
		return image;
	}
	public void setImage(List<ImageDTO> image) {
		this.image = image;
	}
	public List<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	public StatePost getState() {
		return state;
	}
	public void setState(StatePost state) {
		this.state = state;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}

	
}
