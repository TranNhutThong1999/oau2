package com.thong.api.output;

import java.util.Map;
import org.springframework.http.HttpStatus;

public class Error {
	
	private HttpStatus status;
	private Map<String,String> error;
	public Error(HttpStatus status, Map<String, String> error) {
		super();
		this.status = status;
		this.error = error;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Map<String, String> getError() {
		return error;
	}
	public void setError(Map<String, String> error) {
		this.error = error;
	}

	
}
