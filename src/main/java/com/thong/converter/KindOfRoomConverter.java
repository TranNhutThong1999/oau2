package com.thong.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thong.dto.KindOfRoomDTO;
import com.thong.entity.KindOfRoom;

@Component
public class KindOfRoomConverter implements IConverter<KindOfRoom, KindOfRoomDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public KindOfRoom toEntity(KindOfRoomDTO o) {
		// TODO Auto-generated method stub
		return modelMapper.map(o, KindOfRoom.class);
	}

	@Override
	public KindOfRoomDTO toDTO(KindOfRoom o) {
		// TODO Auto-generated method stub
		return  modelMapper.map(o, KindOfRoomDTO.class);
	}


}
