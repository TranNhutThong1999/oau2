package com.thong.service;

import java.util.List;

import com.thong.entity.Role;

public interface IRoleService {
	Role findOneByName(String name);
	List<Role> findAll();
}
