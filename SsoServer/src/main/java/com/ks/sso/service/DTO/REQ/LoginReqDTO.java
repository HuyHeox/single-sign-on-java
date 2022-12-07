package com.ks.sso.service.DTO.REQ;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqDTO implements Serializable {
    public LoginReqDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String username;
    private String password;
}
