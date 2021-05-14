package com.thong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thong.entity.Role;
import com.thong.repository.RoleRepository;
import com.thong.service.IRoleService;

@Service
public class RoleService implements IRoleService{
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findOneByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findOneByName(name);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

}
