package com.example.YTlogin.Entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;

@Table(name = "users")
@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(name="email",nullable = false, unique = true)
	private String email;
	@Column(name="ph_number",unique = true)
	private String ph_number;
	@Column(name="user_ref",unique=true)
	private String user_referral_code;
	@Column(name="user_name")
	private String name;
	@Column(name="dob")
	private String dob;
	@Column(name="address")
	private String address;
	
	@CreationTimestamp
	@Column(name="created_at")
	private Date created_at;

	@Column(name="is_active")
	private Boolean is_active;
	@Column(name="is_deleted")
	private Boolean is_deleted;
	@Column(name="last_modified")
	@UpdateTimestamp
	private Date lastModified;
	
	@OneToMany(targetEntity = ReferralEntity.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "referrer_user_id", referencedColumnName = "Id")
	private List<ReferralEntity> referralEntity;

	 
	@OneToOne(cascade = CascadeType.ALL)
	private LoginDetailsEntity loginDetailsEntity;

	public String getUser_referral_code() {
		return user_referral_code;
	}

	public void setUser_referral_code(String user_referral_code) {
		this.user_referral_code = user_referral_code;
	}

	public Date getLastModified() { 
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPh_number() {
		return ph_number;
	}

	public void setPh_number(String ph_number) {
		this.ph_number = ph_number;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public String getReferral_code() {
		return user_referral_code;
	}

	public void setReferral_code(String referral_code) {
		this.user_referral_code = referral_code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public List<ReferralEntity> getReferralEntity() {
		return referralEntity;
	} 

	public void setReferralEntity(List<ReferralEntity> referralEntity) {
		this.referralEntity = referralEntity;
	}

	public LoginDetailsEntity getLoginDetailsEntity() {
		return loginDetailsEntity;
	}

	public void setLoginDetailsEntity(LoginDetailsEntity loginDetailsEntity) {
		this.loginDetailsEntity = loginDetailsEntity;
	}
	
	@Override
	public String toString() {
		return "UserEntity [Id=" + Id + ", username=" + ", email=" + email + ", ph_number=" + ph_number
				+ ", referral_code=" + user_referral_code + ", dob=" + dob + ", address=" + address + ", created_at="
				+ created_at + ", is_active=" + is_active + ", is_deleted=" + is_deleted
				+ "]";
	}
}
