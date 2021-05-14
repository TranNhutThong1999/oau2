package com.thong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thong.converter.KindOfRoomConverter;
import com.thong.dto.KindOfRoomDTO;
import com.thong.entity.KindOfRoom;
import com.thong.repository.KindOfRoomRepository;
import com.thong.service.IKindOfRoomService;

@Service
public class KindOfRoomService implements IKindOfRoomService {
	@Autowired
	private KindOfRoomRepository kindOfRoomRepository;
	
	@Autowired
	private KindOfRoomConverter kindOfRoomConverter;
	
	@Override
	public void save(KindOfRoomDTO k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<KindOfRoomDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(KindOfRoomDTO k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(KindOfRoomDTO k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KindOfRoom findOneById(int id) {
		// TODO Auto-generated method stub
		return kindOfRoomRepository.findOneById(id);
	}


}
