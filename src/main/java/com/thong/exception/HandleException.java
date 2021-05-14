package com.thong.exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.thong.api.output.Error;
@Component
public class HandleException {
		public Error handle(String message) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", message);
			return new Error(HttpStatus.BAD_REQUEST, map);
		}
}
