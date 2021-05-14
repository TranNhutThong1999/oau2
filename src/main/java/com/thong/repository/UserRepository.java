package com.thong.repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thong.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findOneById(int id);
	Optional<User> findOneByUserName(String username);
	void deleteById(int id);
}