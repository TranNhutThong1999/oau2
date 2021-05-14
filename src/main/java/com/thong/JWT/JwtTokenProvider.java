package com.thong.JWT;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.thong.Cache;
import com.thong.Constants;
import com.thong.Security.MyUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	@Autowired
	private Constants constants;

	@Autowired
	private Cache cache;
	
	// Tạo ra token từ chuỗi authentication
	public String generateToken(Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + constants.getJWT_Expire());
		// mã hóa token
		return constants.getPrefix()+" "+ Jwts.builder().setSubject(Long.toString(user.getId())).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, constants.getJWT_Secret()).compact();
	}

	// Lấy id_user từ token đã được mã hóa
	public int getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(constants.getJWT_Secret()).parseClaimsJws(token).getBody();
		return (int) Long.parseLong(claims.getSubject());
	}
	public Date getTimeExprire(String token) {
		Claims claims = Jwts.parser().setSigningKey(constants.getJWT_Secret()).parseClaimsJws(token).getBody();
		return claims.getExpiration();
	}
	// check token
	public boolean validateToken(String authToken) throws SignatureException {
		try {
			Jwts.parser().setSigningKey(constants.getJWT_Secret()).parseClaimsJws(authToken);
			boolean a =!cache.contains(authToken);
			return a;
		} catch (MalformedJwtException ex) {
			// logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			// logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			// logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			// logger.error("JWT claims string is empty.");
		}
		
		return false;
	}
}
