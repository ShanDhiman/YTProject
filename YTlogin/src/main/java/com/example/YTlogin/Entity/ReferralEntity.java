package com.example.YTlogin.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name="referralDetails")
@Entity
public class ReferralEntity {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String referrerEmail;
	private String referralEmail;	
	private Long points;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getReferrerEmail() {
		return referrerEmail;
	}
	public void setReferrerEmail(String referrerEmail) {
		this.referrerEmail = referrerEmail;
	}
	public String getReferralEmail() {
		return referralEmail;
	}
	public void setReferralEmail(String referralEmail) {
		this.referralEmail = referralEmail;
	}
    
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "ReferralEntity [Id=" + Id + ", refId=" + ", referrer_email=" + referrerEmail
				+ ", referral_email=" + referralEmail + ", points=" + points + "]";
	}
	
}
