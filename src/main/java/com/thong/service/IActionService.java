package com.thong.service;

import com.thong.dto.ActionDTO;

public interface IActionService {
		void save(ActionDTO action) throws Exception;
		void delete(int id);
}
