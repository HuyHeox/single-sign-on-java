package com.ks.sso.model;


import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.ks.sso.config.Config;
import com.ks.sso.model.basic.IBasic;

@Entity
public class ResetPassword implements IBasic {
    @Id private String id = Config.TEXT_EMPTY;
    @Index private String resetKey = Config.NULL_TXT;
    @Index private String email = Config.NULL_TXT;
    private String classType = Config.NULL_TXT;
    private long lastUpdate = Config.NULL_ID;
    @Index private int status =  0;

    public ResetPassword(){
        lastUpdate = new Date().getTime();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(Object id) {
        if (id != null) {
            this.id = id + "";
        }
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
