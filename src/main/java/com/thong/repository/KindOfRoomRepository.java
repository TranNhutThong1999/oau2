package com.thong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thong.entity.KindOfRoom;

public interface KindOfRoomRepository extends JpaRepository<KindOfRoom, Long> {
	KindOfRoom findOneById(int id);
}
