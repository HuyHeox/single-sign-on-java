package com.ks.sso.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class DecodeTokenAccessClaim {
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	private String username;
    private String role;
    private Date expiredDate;

    public DecodeTokenAccessClaim(String username, String role, Date expiredDate) {
        this.username = username;
        this.role = role;
        this.expiredDate = expiredDate;
    }
}
