package com.ks.sso.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.ks.sso.config.Config;
import com.ks.sso.model.basic.IBasic;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserInfo implements IBasic {

    @Ignore
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Index
    private String email = Config.NULL_TXT;
    @Index
    private String phoneNumber = Config.NULL_TXT;
    @Index
    private String password = Config.NULL_TXT;
    @Index
    private Long companyId = Config.NULL_ID;
    @Index
    private Long rootId = Config.NULL_ID;
    @Index
    private Long nationId = Config.NULL_ID;
    @Index
    private List<String> queryIds = new ArrayList<String>();
    private String nationality = Config.NULL_TXT;
    @Index
    private Long birthdate = Config.NULL_ID;
    @Index
    private String tokenKey = Config.TEXT_EMPTY;
    @Index
    private Long roleId = UserInfo.ROLE_COMPANY_STAFF;
    private String fullName = Config.NULL_TXT;
    private String title = Config.NULL_TXT;
    private String avatar = Config.NULL_TXT;
    private String jobDetail = Config.NULL_TXT;
    private String position = Config.NULL_TXT;
    private int gender = -1;
    private String profileUrl = Config.TEXT_EMPTY;
    @Index
    private long lastLoggedIn = Config.NULL_ID;
    @Index
    private Integer status = Config.USER_STATUS_NEW;
    @Index
    private long lastUpdate = Config.NULL_ID;
    @Ignore
    private String sessionId;
    private List<Long> rootIds = new ArrayList<Long>();
    private List<Long> eduProgramIds = new ArrayList<Long>();
    private List<Long> exchangeIds = new ArrayList<Long>();
    private List<Long> confIds = new ArrayList<Long>();
    private List<Long> courseIds = new ArrayList<Long>();
    private List<Long> projectIds = new ArrayList<Long>();
    private List<Long> internIds = new ArrayList<Long>();
    @Ignore
    public static final long ROLE_COMPANY_STAFF = 0;
    @Ignore
    public static final long ROLE_COMPANY_CONTACT = 1;
    @Ignore
    public static final long ROLE_COMPANY_MANAGER = 2;
    @Ignore
    public static final long ROLE_COMPANY_SCHOLAR = 3;
    @Ignore
    public static final long ROLE_COMPANY_STUDENT = 4;

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public void setId(Object id) {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRootId() {
        return rootId;
    }

    public void setRootId(Long rootId) {
        this.rootId = rootId;
    }

    public Long getNationId() {
        return nationId;
    }

    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    public List<String> getQueryIds() {
        return queryIds;
    }

    public void setQueryIds(List<String> queryIds) {
        this.queryIds = queryIds;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Long birthdate) {
        this.birthdate = birthdate;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public long getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(long lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Long> getRootIds() {
        return rootIds;
    }

    public void setRootIds(List<Long> rootIds) {
        this.rootIds = rootIds;
    }

    public List<Long> getEduProgramIds() {
        return eduProgramIds;
    }

    public void setEduProgramIds(List<Long> eduProgramIds) {
        this.eduProgramIds = eduProgramIds;
    }

    public List<Long> getExchangeIds() {
        return exchangeIds;
    }

    public void setExchangeIds(List<Long> exchangeIds) {
        this.exchangeIds = exchangeIds;
    }

    public List<Long> getConfIds() {
        return confIds;
    }

    public void setConfIds(List<Long> confIds) {
        this.confIds = confIds;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }

    public List<Long> getInternIds() {
        return internIds;
    }

    public void setInternIds(List<Long> internIds) {
        this.internIds = internIds;
    }

    public boolean isAvailable() {
        return this.getStatus() != Config.USER_STATUS_QUIT;
    }
}
