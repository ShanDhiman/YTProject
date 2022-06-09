package com.example.YTlogin.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.Id;

@Table(name="login_details")
@Entity
public class LoginDetailsEntity {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date loggedInAt;
	private String login_type;
		
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName = "Id")
	private UserEntity userEntity;
	
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	public String getLogin_type() {
		return login_type;
	}
	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Date getLogindate() {
		return createdAt;
	}
	public void setLogindate(Date logindate) {
		this.createdAt = logindate;
	}
	public Date getLogintime() {
		return loggedInAt;
	}
	public void setLogintime(Date logintime) {
		this.loggedInAt = logintime;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loginDetailsEntity", fetch = FetchType.LAZY)
	private List<DeviceDetailsEntity> deviceDetails;
	
	public List<DeviceDetailsEntity> getDeviceDetails() {
		return deviceDetails;
	}
	public void setDeviceDetails(List<DeviceDetailsEntity> deviceDetails) {
		this.deviceDetails = deviceDetails;
	}	
}
