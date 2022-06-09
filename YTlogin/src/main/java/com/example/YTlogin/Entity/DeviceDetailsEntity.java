package com.example.YTlogin.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Table(name="deviceDetails")
@Entity
public class DeviceDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String devicename;
	private String devicemodel;
	private String token;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	private Boolean isActive;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "login_device_id", referencedColumnName = "Id")
	private LoginDetailsEntity loginDetailsEntity;
	

	public LoginDetailsEntity getLoginDetailsEntity() {
		return loginDetailsEntity;
	}
	public void setLoginDetailsEntity(LoginDetailsEntity loginDetailsEntity) {
		this.loginDetailsEntity = loginDetailsEntity;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getDevicemodel() {
		return devicemodel;
	}
	public void setDevicemodel(String devicemodel) {
		this.devicemodel = devicemodel;
	}
	
}
