package com.thong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thong.entity.Action;

public interface ActionRepository extends JpaRepository<Action, Integer>{
	void deleteById(int id);
}
