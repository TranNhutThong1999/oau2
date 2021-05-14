package com.thong.config.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevodeTokenResourceServer {

	@Resource(name = "tokenServices")
	private DefaultTokenServices defaultTokenServices;

	@Resource(name="tokenStore")
	private TokenStore tokenStore;
	
	@Autowired
	private ResourceServerTokenServices  resourceServerTokenServices;
	
	@DeleteMapping("/oauth/token/revoke")
	@ResponseBody
	public String revokeToken(HttpServletRequest request) {
		
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.contains("Bearer")) {
			String token = authorization.substring("Bearer".length() + 1);
			 OAuth2Authentication auth = resourceServerTokenServices.loadAuthentication(token);
			return "ok";
		}
		return "failure";
	}

	@GetMapping("/tokens")
	@ResponseBody
	public List<String> getTokens() {
	    List<String> tokenValues = new ArrayList<String>();
	    Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("sampleClientId"); 
	    if (tokens!=null){
	        for (OAuth2AccessToken token:tokens){
	            tokenValues.add(token.getValue());
	        }
	    }
	    return tokenValues;
	}
}
