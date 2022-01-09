package com.ta.dtos;

import java.util.List;

public class JwtResponse {

	private String accessToken;
	private String tokenType = "Bearer";
	private String id;
	private String username;
	private String email;
	private boolean admin;
	private List<String> roles;

	public JwtResponse(String accessToken, String id, String username, String email, boolean admin, List<String> roles) {
		this.accessToken = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.admin = admin;
		this.roles = roles;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
