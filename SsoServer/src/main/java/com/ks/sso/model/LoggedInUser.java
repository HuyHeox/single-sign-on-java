package com.ks.sso.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.ks.sso.config.Config;
import com.ks.sso.model.basic.IBasic;

@Entity
public class LoggedInUser implements IBasic {

	@Id private String id;
	private String userId;
	private long date = Config.NULL_ID;
	
	public LoggedInUser() {}

	public LoggedInUser(String id, long userId) {
		this.id = id;
		this.userId = userId + "";
		date = new Date().getTime();
	}

	public String getId() {
		return id;
	}

	@Override
	public void setId(Object id) {
		if (id == null) {
			this.id = null;
		}
		else if (id instanceof String) {
			this.id = (String) id;
		}
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setId(String id) {
		this.id = id;
	}
}