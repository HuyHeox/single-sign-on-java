package com.ks.sso.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginInfo {
    public LoginInfo(String username) {
		super();
		this.username = username;
	}

	private String username;

}
