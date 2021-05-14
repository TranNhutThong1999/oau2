package com.thong.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.thong.entity.Role;
import com.thong.entity.User;
import com.thong.exception.UserNotFoundException;
import com.thong.service.IUserService;

@Component
public class CustomUserDetail implements UserDetailsService{
	@Autowired
	private IUserService userService;
	//add context-can bas-packet
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("user not found")) ;
		List<GrantedAuthority> authortity = new ArrayList<GrantedAuthority>();
		for (Role r : user.getRoles()) {
			authortity.add(new SimpleGrantedAuthority(r.getName()));
		}
		MyUser myUser = new MyUser(user.getUserName(),user.getPassword() , user.isActive(), true, true, user.isNonBlock(), authortity);
		myUser.setEmail(user.getEmail());
		myUser.setFirstName(user.getFirstName());
		myUser.setLastName(user.getLastName());
		myUser.setId(user.getId());
		myUser.setPhone(user.getPhone());
		return myUser;
		
		
	}
	public UserDetails loadUserById(int id) {
		User user = userService.loadUserById(id).orElseGet(null);
		if(user ==null) return null;
		List<GrantedAuthority> authortity = new ArrayList<GrantedAuthority>();
		for (Role r : user.getRoles()) {
			authortity.add(new SimpleGrantedAuthority(r.getName()));
		}
		MyUser myUser = new MyUser(user.getUserName(),user.getPassword() , user.isActive(), true, true, user.isNonBlock(), authortity);
		myUser.setEmail(user.getEmail());
		myUser.setFirstName(user.getFirstName());
		myUser.setLastName(user.getLastName());
		myUser.setId(user.getId());
		myUser.setPhone(user.getPhone());
		return myUser;
	
	}
	public String getPrincipleName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
