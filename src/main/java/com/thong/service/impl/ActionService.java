package com.thong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thong.converter.ActionConverter;
import com.thong.dto.ActionDTO;
import com.thong.repository.ActionRepository;
import com.thong.service.IActionService;

@Service
public class ActionService implements IActionService {
	@Autowired
	private ActionRepository actionRepository;
	
	@Autowired
	private ActionConverter actionConverter;
	
	@Override
	public void save(ActionDTO action) throws Exception {
		// TODO Auto-generated method stub
		actionRepository.save(actionConverter.toEntity(action));
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		actionRepository.deleteById(id);
	}

}
