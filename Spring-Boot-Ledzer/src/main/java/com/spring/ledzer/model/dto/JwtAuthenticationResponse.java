package com.spring.ledzer.model.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by SagarMelmatti on 19/08/17.
 */
public class JwtAuthenticationResponse {
	
	private String accessToken;
	private String tokenType = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtAuthenticationResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
		this.accessToken = accessToken;
		this.username = username;
		this.authorities = authorities;
	}

	public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
	

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
