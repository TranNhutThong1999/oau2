package com.thong.api.output;

import com.thong.dto.UserDTO;

public class LoginOutput {
		private boolean success;
		private String token;
		private UserDTO user;
		
		
		public UserDTO getUser() {
			return user;
		}

		public void setUser(UserDTO user) {
			this.user = user;
		}

		public LoginOutput(boolean success, String token, UserDTO user) {
			super();
			this.success = success;
			this.token = token;
			this.user = user;
		}

		public boolean isSuccess() {
			return success;
		}
	
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		@Override
		public String toString() {
			return "LoginOutput [success=" + success + ", token=" + token + "]";
		}
		
}
