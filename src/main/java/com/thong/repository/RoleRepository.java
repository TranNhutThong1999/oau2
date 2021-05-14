package com.thong.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thong.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findOneByName(String name);

	List<Role> findAll();
}
