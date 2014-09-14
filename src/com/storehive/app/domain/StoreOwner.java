package com.storehive.app.domain;


public class StoreOwner{

	private String id;
	private String username;
	private String name;
	private String surname;
	private String password;
	private String registrationDate; 
	private String deviceID;
	
	public StoreOwner(){}
	
	public String getID() {
		return id;
	}
	public void setID(String iD) {
		id = iD;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	
}
