package com.petsbnb.dto;

public class UserDTO {
	private String userNo;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userZipcode;
	private String userAddress;
	private String userAddressDetail;
	private String userPassword;
	private boolean regSuccess;
	private String fileNo;
	private String fileOrgName;
	private String fileName;
	private String filePath;
	private String imageFileNo;
	private String isPetSitter;
	private String deviceToken;
	
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getIsPetSitter() {
		return isPetSitter;
	}
	public void setIsPetSitter(String isPetSitter) {
		this.isPetSitter = isPetSitter;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileOrgName() {
		return fileOrgName;
	}
	public void setFileOrgName(String fileOrgName) {
		this.fileOrgName = fileOrgName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getImageFileNo() {
		return imageFileNo;
	}
	public void setImageFileNo(String imageFileNo) {
		this.imageFileNo = imageFileNo;
	}
	public boolean getRegSuccess() {
		return regSuccess;
	}
	public void setRegSuccess(boolean regSuccess) {
		this.regSuccess = regSuccess;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserZipcode() {
		return userZipcode;
	}
	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserAddressDetail() {
		return userAddressDetail;
	}
	public void setUserAddressDetail(String userAddressDetail) {
		this.userAddressDetail = userAddressDetail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
