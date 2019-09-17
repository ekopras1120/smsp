package smsp.bean;

import java.util.Date;


public class Staff {
	private int staffId;
	private String idStaff;
	private String staffName;
	private String gender;
	private String batchNumber;
	private Date birthDate;
	private String birthDateString;
	private Date joinDate;
	private String joinDateString;
	private Date resignDate;
	private String resignDateString;
	private String homeAddress;
	private String currentAddress;
	private String phoneNumber;
	private String mobileNumber;
	private String photoFilename;
	private String skills;

	
	private int univId;
	private int positionId;
	private int statusId;
	
	private String position;
	private String univ;
	private String status;


	public String getIdStaff() {
		return idStaff;
	}
	public void setIdStaff(String idStaff) {
		this.idStaff = idStaff;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	} 
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPhotoFilename() {
		return photoFilename;
	}
	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public int getUnivId() {
		return univId;
	}
	public void setUnivId(int univId) {
		this.univId = univId;
	}

	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUniv() {
		return univ;
	}
	public void setUniv(String univ) {
		this.univ = univ;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	} 
	
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public Date getResignDate() {
		return resignDate;
	}
	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}
	public String getJoinDateString() {
		return joinDateString;
	}
	public void setJoinDateString(String joinDateString) {
		this.joinDateString = joinDateString;
	}
	public String getBirthDateString() {
		return birthDateString;
	}
	public void setBirthDateString(String birthDateString) {
		this.birthDateString = birthDateString;
	}
	public String getResignDateString() {
		return resignDateString;
	}
	public void setResignDateString(String resignDateString) {
		this.resignDateString = resignDateString;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}

}
