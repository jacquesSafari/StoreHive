package com.storehive.app.domain;

import java.io.Serializable;

import org.joda.time.DateTime;

public class StoreOwner implements Serializable{

	private static final long serialVersionUID = -2594845867839372868L;
	
	private String ID;
	private String username;
	private String name;
	private String surname;
	private String password;
	private String registrationDate;
	
	public StoreOwner(){}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
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
	
	
}
