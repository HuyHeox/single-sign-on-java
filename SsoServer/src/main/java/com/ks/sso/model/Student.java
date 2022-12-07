package com.ks.sso.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.ks.sso.config.Config;
import com.ks.sso.config.FieldJson;
import com.ks.sso.model.basic.IBasic;

/**
 * Thông tin của SV
 * @author thnguyen
 *
 */
@Entity
public class Student implements IBasic {

//	@FieldJson("SO_BAO_HIEM")
//	@FieldJson("TON_GIAO_ID")
//	@FieldJson("KHUYET_TAT_ID")
//	@FieldJson("NGAY_VAO_DOAN")
//	@FieldJson("NGAY_VAO_DANG")
//	@FieldJson("NGAY_VAO_DANG_CHINH_THUC")

    @Id
    Long id;
    @Index
    String studentId = Config.NULL_TXT;
    @FieldJson("HO_TEN")
    private String fullName = Config.NULL_TXT;
    @JsonIgnore
    private String password = Config.NULL_TXT;
    @FieldJson("NGAY_SINH")
    private long birthdate = Config.NULL_ID;
    private String birthdateStr = Config.TEXT_EMPTY;
    private String birthPlace = Config.TEXT_EMPTY;
    @Index
    private long departmentId = Config.NULL_ID;
    @Index
    private long programId = Config.NULL_ID;                //4: DHCQ
    @Index
    private long majorId = Config.NULL_ID;
    private String majorName = Config.NULL_TXT;
    @Index
    private String majorCode = Config.NULL_TXT;
    @Index
    private String studentYear = Config.NULL_TXT;        //K50, K51, K52
    @Index
    private int year = 0;        //2015, 2016, ...
    @FieldJson("GIOI_TINH_ID")
    @Index
    private int gender = Config.GENDER_MALE;
    @Index
    private String tokenKey = Config.TEXT_EMPTY;
    @FieldJson("DAN_TOC_ID")
    @Index
    private String ethnicity = Config.NULL_TXT;
    @FieldJson("DIEN_THOAI")
    private String phoneNumber = Config.NULL_TXT;
    @JsonIgnore
    private String englishInfo = Config.NULL_TXT;
    @JsonIgnore
    private String creditInfo = Config.NULL_TXT;
    @JsonIgnore
    private double cpa = 0.0;
    @JsonIgnore
    private int totalCredit = 0;
    private long graduationDate = Config.NULL_ID;
    @FieldJson("EMAIL")
    @Index
    private String email = Config.NULL_TXT;
    @Index
    private String personalEmail = Config.NULL_TXT;
    @JsonIgnore
    @Index
    private String idNumber = Config.TEXT_EMPTY;
    @JsonIgnore
    private String idNumberPlace = Config.TEXT_EMPTY;
    @JsonIgnore
    private Long idNumberPlaceId = Config.NULL_ID;
    @FieldJson("CMTND")
    @JsonIgnore
    private Long idNumberDate = Config.NULL_ID;
    @Index
    private long lastLoggedIn = Config.NULL_ID;
    @Index
    private int status = Config.USER_STATUS_NORMAL;
    @Index
    private Long rootId = Config.NULL_ID;
    @Index
    private Long oldRootId = Config.NULL_ID;
    @Index
    private Long uniId = Config.NULL_ID;
    @Index
    private Long classId = Config.NULL_ID;
    @Index
    private String lastSemester = Config.NULL_TXT;
    @Index
    private List<String> semesters = new ArrayList<String>();
    @Index
    private Integer vaccinated = -1;
    private Long familyPolicyId = Config.NULL_ID;
    private List<String> docUrls = new ArrayList<String>();
    private int classOrder = 0;
    private String cvUrl = Config.NULL_TXT;
    private String avatarUrl = Config.NULL_TXT;
    private String className = Config.NULL_TXT;
    private String companyName = Config.NULL_TXT;
    private String scholarShip = Config.NULL_TXT;
    private String position = Config.NULL_TXT;
    private String notes = Config.NULL_TXT;
    @JsonIgnore
    private String address = Config.TEXT_EMPTY;
    @Index
    private Long countryId = Config.NULL_ID;
    private String countryName = Config.TEXT_EMPTY;
    @Index
    private Long provinceId = Config.NULL_ID;
    private Long districtId = Config.NULL_ID;
    private Long communeId = Config.NULL_ID;
    @FieldJson("XA_PHUONG_ID")
    private String homeCommune = Config.TEXT_EMPTY;    //Xã
    @FieldJson("QUAN_HUYEN_ID")
    private String homeDistrict = Config.TEXT_EMPTY; //Huyện
    @FieldJson("TINH_THANH_ID")
    private String homeProvince = Config.TEXT_EMPTY; //Tỉnh
    private String homeAddress = Config.TEXT_EMPTY; //Tỉnh
    private String facebookToken = Config.NULL_TXT;
    @Index
    private int studyMode = 0;                // Chính quy, VB2, VHVL, ...
    private String studentType = Config.TEXT_EMPTY;
    private String profileUrl = Config.NULL_TXT;
    @Index
    private String facebookId = Config.NULL_TXT;
    @Index
    private String relatedEmail = Config.NULL_TXT;
    @Index
    private long createdDate = Config.NULL_ID;
    @Index
    private long lastUpdate = Config.NULL_ID;
    @Index
    private int tmpState = -1;
    private String schoolName = Config.NULL_TXT;
    private String facebookName = Config.NULL_TXT;
    private String contactPerson = Config.NULL_TXT;
    private Long contactProvinceId = Config.NULL_ID;
    private Long contactDistrictId = Config.NULL_ID;
    private Long contactCommuneId = Config.NULL_ID;
    private String contactCommune = Config.TEXT_EMPTY;    //Xã
    private String contactDistrict = Config.TEXT_EMPTY; //Huyện
    private String contactProvince = Config.TEXT_EMPTY; //Tỉnh
    private String contactAddress = Config.TEXT_EMPTY; //Tỉnh
    private String receptInfo = Config.TEXT_EMPTY;
    private String groupName = Config.NULL_TXT;
	@Ignore private String sessionId = Config.NULL_TXT;

    public static final int TYPE_TRAINING_REGULAR = 1;//Chính quy
    public static final int TYPE_TRAINING_DEGREE_2 = 2;//Văn bằng 2
    public static final int TYPE_TRAINING_WORK_STUDY = 3;//Vừa học vừa làm


    @Override
    public Object getId() {
        return this.id;
    }

    @Override
    public void setId(Object id) {
    	this.id = (long)id;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdateStr() {
        return birthdateStr;
    }

    public void setBirthdateStr(String birthdateStr) {
        this.birthdateStr = birthdateStr;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEnglishInfo() {
        return englishInfo;
    }

    public void setEnglishInfo(String englishInfo) {
        this.englishInfo = englishInfo;
    }

    public String getCreditInfo() {
        return creditInfo;
    }

    public void setCreditInfo(String creditInfo) {
        this.creditInfo = creditInfo;
    }

    public double getCpa() {
        return cpa;
    }

    public void setCpa(double cpa) {
        this.cpa = cpa;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    public long getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(long graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumberPlace() {
        return idNumberPlace;
    }

    public void setIdNumberPlace(String idNumberPlace) {
        this.idNumberPlace = idNumberPlace;
    }

    public Long getIdNumberPlaceId() {
        return idNumberPlaceId;
    }

    public void setIdNumberPlaceId(Long idNumberPlaceId) {
        this.idNumberPlaceId = idNumberPlaceId;
    }

    public Long getIdNumberDate() {
        return idNumberDate;
    }

    public void setIdNumberDate(Long idNumberDate) {
        this.idNumberDate = idNumberDate;
    }

    public long getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(long lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getRootId() {
        return rootId;
    }

    public void setRootId(Long rootId) {
        this.rootId = rootId;
    }

    public Long getOldRootId() {
        return oldRootId;
    }

    public void setOldRootId(Long oldRootId) {
        this.oldRootId = oldRootId;
    }

    public Long getUniId() {
        return uniId;
    }

    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getLastSemester() {
        return lastSemester;
    }

    public void setLastSemester(String lastSemester) {
        this.lastSemester = lastSemester;
    }

    public List<String> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<String> semesters) {
        this.semesters = semesters;
    }

    public Integer getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Integer vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Long getFamilyPolicyId() {
        return familyPolicyId;
    }

    public void setFamilyPolicyId(Long familyPolicyId) {
        this.familyPolicyId = familyPolicyId;
    }

    public List<String> getDocUrls() {
        return docUrls;
    }

    public void setDocUrls(List<String> docUrls) {
        this.docUrls = docUrls;
    }

    public int getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(int classOrder) {
        this.classOrder = classOrder;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getScholarShip() {
        return scholarShip;
    }

    public void setScholarShip(String scholarShip) {
        this.scholarShip = scholarShip;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Long communeId) {
        this.communeId = communeId;
    }

    public String getHomeCommune() {
        return homeCommune;
    }

    public void setHomeCommune(String homeCommune) {
        this.homeCommune = homeCommune;
    }

    public String getHomeDistrict() {
        return homeDistrict;
    }

    public void setHomeDistrict(String homeDistrict) {
        this.homeDistrict = homeDistrict;
    }

    public String getHomeProvince() {
        return homeProvince;
    }

    public void setHomeProvince(String homeProvince) {
        this.homeProvince = homeProvince;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public int getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(int studyMode) {
        this.studyMode = studyMode;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getRelatedEmail() {
        return relatedEmail;
    }

    public void setRelatedEmail(String relatedEmail) {
        this.relatedEmail = relatedEmail;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getTmpState() {
        return tmpState;
    }

    public void setTmpState(int tmpState) {
        this.tmpState = tmpState;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Long getContactProvinceId() {
        return contactProvinceId;
    }

    public void setContactProvinceId(Long contactProvinceId) {
        this.contactProvinceId = contactProvinceId;
    }

    public Long getContactDistrictId() {
        return contactDistrictId;
    }

    public void setContactDistrictId(Long contactDistrictId) {
        this.contactDistrictId = contactDistrictId;
    }

    public Long getContactCommuneId() {
        return contactCommuneId;
    }

    public void setContactCommuneId(Long contactCommuneId) {
        this.contactCommuneId = contactCommuneId;
    }

    public String getContactCommune() {
        return contactCommune;
    }

    public void setContactCommune(String contactCommune) {
        this.contactCommune = contactCommune;
    }

    public String getContactDistrict() {
        return contactDistrict;
    }

    public void setContactDistrict(String contactDistrict) {
        this.contactDistrict = contactDistrict;
    }

    public String getContactProvince() {
        return contactProvince;
    }

    public void setContactProvince(String contactProvince) {
        this.contactProvince = contactProvince;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getReceptInfo() {
        return receptInfo;
    }

    public void setReceptInfo(String receptInfo) {
        this.receptInfo = receptInfo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getSessionId() {
		return sessionId;
	}
    
    public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
    
	public static String getStudentId(String email) {
		if (email.contains("@sis.hust.edu.vn")) {
			String studentId = email.substring(0, email.indexOf("@"));
			if (studentId.length() > 7) {
				if (studentId.toLowerCase().endsWith("m")) {
					studentId = studentId.substring(studentId.length() - 7).toUpperCase();
				}
				else {
					studentId = studentId.substring(studentId.length() - 6);
				}
			}
			if (studentId.length() >= 4) {
				return "20" + studentId;
			}
		}
		return email;
	}
}