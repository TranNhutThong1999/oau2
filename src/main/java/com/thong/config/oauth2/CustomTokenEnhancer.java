package com.thong.config.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.thong.dto.UserDTO;
import com.thong.exception.UserNotFoundException;
import com.thong.service.IUserService;

public class CustomTokenEnhancer implements TokenEnhancer{
	@Autowired
	private IUserService userService;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		Map<String, Object> additionalInfo = new HashMap<>();
		try {
			UserDTO u = userService.findOneByUserName(authentication.getName());
			additionalInfo.put("user", u);
		} catch (UserNotFoundException e) {
		}
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
