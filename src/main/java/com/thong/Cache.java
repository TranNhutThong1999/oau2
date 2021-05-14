package com.thong;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thong.JWT.JwtTokenProvider;

import net.jodah.expiringmap.ExpiringMap;

@Component
public class Cache {
	
	private final ExpiringMap<String, String> tokenExpired;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public Cache() {
		this.tokenExpired = ExpiringMap.builder().variableExpiration().build();
	}

	public void addTokenExprireToMap(String token) {
			long timeExpire = jwtTokenProvider.getTimeExprire(token).toInstant().getEpochSecond();
			long timeLogout = new Date().toInstant().getEpochSecond();
			tokenExpired.put(token, "ok",timeExpire-timeLogout,TimeUnit.SECONDS);
	}

	public boolean contains(String token) {
		System.out.println(this.tokenExpired.toString());
		boolean a = this.tokenExpired.containsKey(token.trim());
		return a;
	}
	public static void main(String[] args) {
		ExpiringMap<String, String> tokenExpired = ExpiringMap.builder().variableExpiration().build();
		tokenExpired.put("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzNSIsImlhdCI6MTYwOTI0NTE0OSwiZXhwIjoxNjEwMTA5MTQ5fQ.EZSPBun_vxslBKp5vjXr-4MFWgobZJVMGjdL_INEttK2F99UtD23Ien_M_frhUB98FUrizF7MWYjAGQ_p8pU0g", "ok");
		System.out.println(tokenExpired.containsKey("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzNSIsImlhdCI6MTYwOTI0NTE0OSwiZXhwIjoxNjEwMTA5MTQ5fQ.EZSPBun_vxslBKp5vjXr-4MFWgobZJVMGjdL_INEttK2F99UtD23Ien_M_frhUB98FUrizF7MWYjAGQ_p8pU0g"));
	}
}
