package com.storehive.app.domain;

public class Buyer {

	private String id;
	private String deviceId;
	private double latitude;
	private double longitude;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Buyer [id=" + id + ", deviceId=" + deviceId + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
	
	
}
