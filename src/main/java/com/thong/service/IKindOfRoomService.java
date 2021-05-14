package com.thong.service;

import java.util.List;

import com.thong.dto.KindOfRoomDTO;
import com.thong.entity.KindOfRoom;

public interface IKindOfRoomService {
	void save(KindOfRoomDTO k);
	List<KindOfRoomDTO> findAll();
	void delete(KindOfRoomDTO k);
	void update(KindOfRoomDTO k);
	KindOfRoom findOneById(int id);
}
