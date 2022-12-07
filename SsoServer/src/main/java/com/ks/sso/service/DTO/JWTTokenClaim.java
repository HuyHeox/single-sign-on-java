package com.ks.sso.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class JWTTokenClaim {

    public JWTTokenClaim(String email, Date expiredDate) {
		super();
		this.email = email;
		this.expiredDate = expiredDate;
	}
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	private String email;
    private Date expiredDate;

}
