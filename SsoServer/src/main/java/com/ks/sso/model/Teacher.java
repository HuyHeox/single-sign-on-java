package com.ks.sso.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.ks.sso.config.FieldJson;
import com.ks.sso.model.basic.IBasic;
import com.ks.sso.config.Config;

/**
 * Thông tin Cán bộ
 * @author thnguyen
 *
 */
@Entity
public class Teacher implements IBasic {

    @Ignore
    public static final int STAFF_STATUS_UNDEFINED = -1;
    @Ignore
    public static final int STAFF_STATUS_NORMAL = 0;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @FieldJson("ID")
    @Id private Long id;
    @Index private String userName = Config.NULL_TXT;
    @JsonIgnore @Index private String password = Config.NULL_TXT;
    @FieldJson("TEN_GOI")
    @Index private String fullName = Config.NULL_TXT;
    @Index private String firstName = Config.NULL_TXT;
    @Index private String lastName = Config.NULL_TXT;
    @FieldJson("GIOI_TINH_ID")
    @Index private Integer gender = Config.GENDER_MALE;
    @FieldJson("CAN_BO_ID")
    @Index private String staffCode = Config.NULL_TXT;			//CAN_BO_ID
    @Index private Integer age = 0;
    private String commonName = Config.NULL_TXT;
    @Index private Long title = Config.NULL_ID;
    @Index private Long degree = Config.NULL_ID;
    @Index private Long jobType = Config.NULL_ID;
    @Index private String jobName = Config.NULL_TXT;
    @Index private Long contractType = Config.NULL_ID;
    @JsonIgnore private String contractNotes = Config.NULL_TXT;
    @Index private Long position = Config.NULL_ID; // Position
    @Index private Long departmentId = Config.NULL_ID; // 0: CNPM, 1: HTTT, 2: KHMT
    @Index private Long rootId = Config.NULL_ID;// CNTT, Dien
    @Index private Long uniId = Config.NULL_ID;
    @Index private Integer source = 1; // 1: from QLDT; 2: from QLCB
    @Index private Integer vaccinated = -1;
    @Index private Long partyUnitId = Config.NULL_ID;
    private String partyUnitName = Config.NULL_TXT;
    @Index private Long unionUnitId = Config.NULL_ID;
    private String unionUnitName = Config.NULL_TXT;

    @JsonIgnore private List<String> semesters = new ArrayList<String>();
    //	@JsonIgnore private String taxNumber = Config.NULL_TXT;
    @JsonIgnore private String notes = Config.NULL_TXT;
    //	@JsonIgnore private String idNumber = Config.NULL_TXT;
//	@JsonIgnore private String idNumberPlace = Config.NULL_TXT;
//	@JsonIgnore private Long idNumberPlaceId = Config.NULL_ID;
//	@JsonIgnore private Long idNumberDate = Config.NULL_ID;
//	@JsonIgnore private String idNumberFront = Config.NULL_TXT;
//	@JsonIgnore private String idNumberBack = Config.NULL_TXT;
//	@JsonIgnore private String assuranceNumber = Config.TEXT_EMPTY;
//	@JsonIgnore private Long assuranceDate = Config.NULL_ID;
    @JsonIgnore private Long stateOfficialDate = Config.NULL_ID;		//Ngày vào biên chế
    @JsonIgnore private Long prevHiredDate = Config.NULL_ID;
    @JsonIgnore private String prevPosition = Config.NULL_TXT;
    @JsonIgnore private String prevCompany = Config.NULL_TXT;
    @FieldJson("NGAY_TUYEN_DUNG")
    private Long hiredDate = Config.NULL_ID;
    @JsonIgnore private String hiredPosition = Config.NULL_TXT;
    @JsonIgnore private Long contractEndDate = Config.NULL_ID;
    @JsonIgnore private String contractEndNumber = Config.NULL_TXT;
    private String nickName = Config.NULL_TXT;
    @FieldJson("TRINH_DO_QLNN_ID")
    private Long qlnnId = Config.NULL_ID;
    @FieldJson("TRINH_DO_LLCT_ID")
    private Long llctId = Config.NULL_ID;
    @FieldJson("TRINH_DO_TIN_HOC_ID")
    private Long itLevelId = Config.NULL_ID;
    private Long seniorityDate = Config.NULL_ID;
    @Index private String refCode = Config.NULL_TXT;
    @Index private Long salaryNextDate = Config.NULL_ID;
    @Index private Long seniorityNextDate = Config.NULL_ID;
    @Index private Long salaryLastDate = Config.NULL_ID;
    @Index private Long seniorityLastDate = Config.NULL_ID;
    @Index private Integer salaryInterval = -1;

    //	@JsonIgnore private String bankAccount = Config.NULL_TXT;
//	@JsonIgnore private String bankInfo = Config.NULL_TXT;
    @JsonIgnore private String website = Config.NULL_TXT;
    @JsonIgnore private String researchBio = Config.NULL_TXT;
    @JsonIgnore private String personalBio = Config.NULL_TXT;
    @JsonIgnore private String profileUrl = Config.NULL_TXT;

    @JsonIgnore @Index private Integer candidateGroup = -1;
    @JsonIgnore @Index private Long recruitTime = Config.NULL_ID;
    @JsonIgnore private String researchPlan = Config.NULL_TXT;
    @JsonIgnore private String researchResume = Config.NULL_TXT;

    @Index private Long birthDate = null;// new Date();
    private String birthPlace = Config.NULL_TXT;
    private Long birthPlaceId = Config.NULL_ID;
    @Index private String birthDateStr = Config.NULL_TXT;
    @FieldJson("EMAIL")
    @Index private String workEmail = Config.NULL_TXT;
    private String personalEmail = Config.NULL_TXT;
    @FieldJson("DIEN_THOAI")
    private String workPhoneNumber = Config.NULL_TXT;
    @JsonIgnore private String cellPhoneNumber = Config.NULL_TXT;
    @JsonIgnore private List<String> otherEmails = new ArrayList<String>();
    @JsonIgnore private List<String> otherPhoneNumbers = new ArrayList<String>();
    @JsonIgnore private String workAddress = Config.NULL_TXT;

    @FieldJson("NGAY_VAO_DANG")
    @Index private Long partyDate = Config.NULL_ID;
    @FieldJson("NGAY_VAO_DANG_CHINH_THUC")
    @Index private Long officialPartyDate = Config.NULL_ID;
//	@JsonProperty("NGAY_NHAP_NGU")
//	@JsonIgnore private Long enlistmentDate = Config.NULL_ID;
//	@JsonProperty("NGAY_XUAT_NGU")
//	@JsonIgnore private Long dischargeDate = Config.NULL_ID;

    @JsonIgnore private Long marriedStatus = Config.NULL_ID;
    @JsonIgnore private String info = Config.NULL_TXT;
    @JsonIgnore private String facebookToken = Config.NULL_TXT;
    @JsonIgnore @Index private String facebookId = Config.NULL_TXT;
    @JsonIgnore private String facebookName = Config.NULL_TXT;
    @JsonIgnore @Index private String relatedEmail = Config.NULL_TXT;
    @JsonIgnore @Index private long lastLoggedIn = Config.NULL_ID;
    private String avatarUrl = Config.NULL_TXT;
    private int projectStatus = Config.STATUS_OPENNED;
    @JsonIgnore private List<Long> courseIds = new ArrayList<Long>();
    private Integer activated = 0;
    @Index private Long roomId = Config.NULL_ID;
    private String roomName = Config.TEXT_EMPTY;
    @JsonIgnore @Index private Integer probation = -1;
    @JsonIgnore @Index private Long probationFrom = Config.NULL_ID;
    @JsonIgnore @Index private Long probationTo = Config.NULL_ID;
    @JsonIgnore @Index private Long salaryRaiseDate = Config.NULL_ID;
    @FieldJson("TRANG_THAI_CAN_BO_ID")
    @Index private Integer status = Teacher.STAFF_STATUS_NORMAL;
    @Index private Integer tmpStatus = Teacher.STAFF_STATUS_UNDEFINED;
    @Index private long lastUpdate = Config.NULL_ID;
    //	@JsonIgnore @Index private String tokenKey = Config.TEXT_EMPTY;
    private Long currentRoleId = 0L;
    @JsonIgnore private double kVt = 0.0;
    @JsonIgnore private List<Long> roleIds = new ArrayList<Long>();
    @FieldJson("DAN_TOC_ID")
    private String nation = Config.NULL_TXT;
    @FieldJson("TON_GIAO_ID")
    private String religion = Config.NULL_TXT;
	@Ignore private String sessionId = Config.NULL_TXT;
    @JsonIgnore private List<String> docUrls = new ArrayList<String>();
    @JsonIgnore private List<Long> mainResearchTopics = new ArrayList<Long>();
    @JsonIgnore private List<Long> relatedResearchTopics = new ArrayList<Long>();


    @Override
    public Object getId() {
        return id;
    }

    @Override
    public void setId(Object id) {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public Long getTitle() {
        return title;
    }

    public void setTitle(Long title) {
        this.title = title;
    }

    public Long getDegree() {
        return degree;
    }

    public void setDegree(Long degree) {
        this.degree = degree;
    }

    public Long getJobType() {
        return jobType;
    }

    public void setJobType(Long jobType) {
        this.jobType = jobType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getContractType() {
        return contractType;
    }

    public void setContractType(Long contractType) {
        this.contractType = contractType;
    }

    public String getContractNotes() {
        return contractNotes;
    }

    public void setContractNotes(String contractNotes) {
        this.contractNotes = contractNotes;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getRootId() {
        return rootId;
    }

    public void setRootId(Long rootId) {
        this.rootId = rootId;
    }

    public Long getUniId() {
        return uniId;
    }

    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Integer vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Long getPartyUnitId() {
        return partyUnitId;
    }

    public void setPartyUnitId(Long partyUnitId) {
        this.partyUnitId = partyUnitId;
    }

    public String getPartyUnitName() {
        return partyUnitName;
    }

    public void setPartyUnitName(String partyUnitName) {
        this.partyUnitName = partyUnitName;
    }

    public Long getUnionUnitId() {
        return unionUnitId;
    }

    public void setUnionUnitId(Long unionUnitId) {
        this.unionUnitId = unionUnitId;
    }

    public String getUnionUnitName() {
        return unionUnitName;
    }

    public void setUnionUnitName(String unionUnitName) {
        this.unionUnitName = unionUnitName;
    }

    public List<String> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<String> semesters) {
        this.semesters = semesters;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getStateOfficialDate() {
        return stateOfficialDate;
    }

    public void setStateOfficialDate(Long stateOfficialDate) {
        this.stateOfficialDate = stateOfficialDate;
    }

    public Long getPrevHiredDate() {
        return prevHiredDate;
    }

    public void setPrevHiredDate(Long prevHiredDate) {
        this.prevHiredDate = prevHiredDate;
    }

    public String getPrevPosition() {
        return prevPosition;
    }

    public void setPrevPosition(String prevPosition) {
        this.prevPosition = prevPosition;
    }

    public String getPrevCompany() {
        return prevCompany;
    }

    public void setPrevCompany(String prevCompany) {
        this.prevCompany = prevCompany;
    }

    public Long getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(Long hiredDate) {
        this.hiredDate = hiredDate;
    }

    public String getHiredPosition() {
        return hiredPosition;
    }

    public void setHiredPosition(String hiredPosition) {
        this.hiredPosition = hiredPosition;
    }

    public Long getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Long contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractEndNumber() {
        return contractEndNumber;
    }

    public void setContractEndNumber(String contractEndNumber) {
        this.contractEndNumber = contractEndNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getQlnnId() {
        return qlnnId;
    }

    public void setQlnnId(Long qlnnId) {
        this.qlnnId = qlnnId;
    }

    public Long getLlctId() {
        return llctId;
    }

    public void setLlctId(Long llctId) {
        this.llctId = llctId;
    }

    public Long getItLevelId() {
        return itLevelId;
    }

    public void setItLevelId(Long itLevelId) {
        this.itLevelId = itLevelId;
    }

    public Long getSeniorityDate() {
        return seniorityDate;
    }

    public void setSeniorityDate(Long seniorityDate) {
        this.seniorityDate = seniorityDate;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public Long getSalaryNextDate() {
        return salaryNextDate;
    }

    public void setSalaryNextDate(Long salaryNextDate) {
        this.salaryNextDate = salaryNextDate;
    }

    public Long getSeniorityNextDate() {
        return seniorityNextDate;
    }

    public void setSeniorityNextDate(Long seniorityNextDate) {
        this.seniorityNextDate = seniorityNextDate;
    }

    public Long getSalaryLastDate() {
        return salaryLastDate;
    }

    public void setSalaryLastDate(Long salaryLastDate) {
        this.salaryLastDate = salaryLastDate;
    }

    public Long getSeniorityLastDate() {
        return seniorityLastDate;
    }

    public void setSeniorityLastDate(Long seniorityLastDate) {
        this.seniorityLastDate = seniorityLastDate;
    }

    public Integer getSalaryInterval() {
        return salaryInterval;
    }

    public void setSalaryInterval(Integer salaryInterval) {
        this.salaryInterval = salaryInterval;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResearchBio() {
        return researchBio;
    }

    public void setResearchBio(String researchBio) {
        this.researchBio = researchBio;
    }

    public String getPersonalBio() {
        return personalBio;
    }

    public void setPersonalBio(String personalBio) {
        this.personalBio = personalBio;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Integer getCandidateGroup() {
        return candidateGroup;
    }

    public void setCandidateGroup(Integer candidateGroup) {
        this.candidateGroup = candidateGroup;
    }

    public Long getRecruitTime() {
        return recruitTime;
    }

    public void setRecruitTime(Long recruitTime) {
        this.recruitTime = recruitTime;
    }

    public String getResearchPlan() {
        return researchPlan;
    }

    public void setResearchPlan(String researchPlan) {
        this.researchPlan = researchPlan;
    }

    public String getResearchResume() {
        return researchResume;
    }

    public void setResearchResume(String researchResume) {
        this.researchResume = researchResume;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Long getBirthPlaceId() {
        return birthPlaceId;
    }

    public void setBirthPlaceId(Long birthPlaceId) {
        this.birthPlaceId = birthPlaceId;
    }

    public String getBirthDateStr() {
        return birthDateStr;
    }

    public void setBirthDateStr(String birthDateStr) {
        this.birthDateStr = birthDateStr;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public List<String> getOtherEmails() {
        return otherEmails;
    }

    public void setOtherEmails(List<String> otherEmails) {
        this.otherEmails = otherEmails;
    }

    public List<String> getOtherPhoneNumbers() {
        return otherPhoneNumbers;
    }

    public void setOtherPhoneNumbers(List<String> otherPhoneNumbers) {
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public Long getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(Long partyDate) {
        this.partyDate = partyDate;
    }

    public Long getOfficialPartyDate() {
        return officialPartyDate;
    }

    public void setOfficialPartyDate(Long officialPartyDate) {
        this.officialPartyDate = officialPartyDate;
    }

    public Long getMarriedStatus() {
        return marriedStatus;
    }

    public void setMarriedStatus(Long marriedStatus) {
        this.marriedStatus = marriedStatus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getRelatedEmail() {
        return relatedEmail;
    }

    public void setRelatedEmail(String relatedEmail) {
        this.relatedEmail = relatedEmail;
    }

    public long getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(long lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getProbation() {
        return probation;
    }

    public void setProbation(Integer probation) {
        this.probation = probation;
    }

    public Long getProbationFrom() {
        return probationFrom;
    }

    public void setProbationFrom(Long probationFrom) {
        this.probationFrom = probationFrom;
    }

    public Long getProbationTo() {
        return probationTo;
    }

    public void setProbationTo(Long probationTo) {
        this.probationTo = probationTo;
    }

    public Long getSalaryRaiseDate() {
        return salaryRaiseDate;
    }

    public void setSalaryRaiseDate(Long salaryRaiseDate) {
        this.salaryRaiseDate = salaryRaiseDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTmpStatus() {
        return tmpStatus;
    }

    public void setTmpStatus(Integer tmpStatus) {
        this.tmpStatus = tmpStatus;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(Long currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public double getkVt() {
        return kVt;
    }

    public void setkVt(double kVt) {
        this.kVt = kVt;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public List<String> getDocUrls() {
        return docUrls;
    }

    public void setDocUrls(List<String> docUrls) {
        this.docUrls = docUrls;
    }

    public List<Long> getMainResearchTopics() {
        return mainResearchTopics;
    }

    public void setMainResearchTopics(List<Long> mainResearchTopics) {
        this.mainResearchTopics = mainResearchTopics;
    }

    public List<Long> getRelatedResearchTopics() {
        return relatedResearchTopics;
    }

    public void setRelatedResearchTopics(List<Long> relatedResearchTopics) {
        this.relatedResearchTopics = relatedResearchTopics;
    }
    
    public String getSessionId() {
		return sessionId;
	}
    
    public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
