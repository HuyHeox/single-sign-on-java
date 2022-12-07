package com.ks.sso.service.DTO;

import lombok.Data;

@Data
public class AccessToken {
    public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	private String accessToken;
    private String refreshToken;
}
