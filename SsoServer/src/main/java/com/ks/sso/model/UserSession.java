package com.ks.sso.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.ks.sso.config.Config;
import com.ks.sso.model.basic.IBasic;
import com.ks.sso.util.ServerUtils;

/**
 * Lưu thông tin của 1 người dùng đăng nhập
 * @author thnguyen
 *
 */
@Entity
public class UserSession implements IBasic {

	@Id private Long id;
	@Index private Long userId = Config.NULL_ID;
	private String name = Config.NULL_TXT;
	@Index private String type = Config.NULL_TXT;
	@Index private String sessionId = Config.NULL_TXT;
	@Index private Long created = Config.NULL_ID;
	@Index private Long updated = Config.NULL_ID;
	private Integer os = -1;
	private Integer sso = -1;
	@Ignore private IBasic user = null;
	@Ignore private String email = null;

	
	@Ignore public static Integer OS_WEB = 1;
	@Ignore public static Integer OS_ANDROID = 2;
	@Ignore public static Integer OS_ISO = 3;
		
	public UserSession() {}

	public UserSession(Long userId, String name, String type, String sessionId, int os, int sso) {
		this.setUserId(userId);
		this.setName(name);
		this.setType(type);
		this.setSessionId(sessionId);
		this.setOs(os);
		this.setSso(sso);
		Long now = ServerUtils.getCurrentTime();
		this.setCreated(now);
		this.setUpdated(now);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Object id) {
		if (id == null) {
			this.id = null;
		}
		else if (id instanceof String) {
			this.id = (Long) id;
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}

	public Integer getSso() {
		return sso;
	}

	public void setSso(Integer sso) {
		this.sso = sso;
	}

	public IBasic getUser() {
		return user;
	}

	public void setUser(IBasic user) {
		this.user = user;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}