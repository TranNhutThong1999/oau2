package com.thong.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Token extends Common {
	private String decription;
	private Timestamp expire;
	private String token;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	
	public Token() {
		super();
		createToken();
		
	}

	public boolean isAfterTime() {
		Calendar time = Calendar.getInstance();
		Timestamp timetamp = new Timestamp(time.getTime().getTime());
		return timetamp.after(this.expire);
	}

	public void createToken() {
		this.token = UUID.randomUUID().toString();
	}

	public void setTimeTokenFuture(int minutes) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MINUTE, minutes);
		this.expire = new Timestamp(time.getTime().getTime());
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	public Timestamp getExpire() {
		return expire;
	}

	public void setExpire(Timestamp expire) {
		this.expire = expire;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
