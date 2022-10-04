package com.greenmart.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="userpayment")
public class UserPayment extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="up_id",nullable = false)
	private int upId;
	@Column(name="user_id",nullable = false)
	private int userId;
	@Column(name="payment_type", length = 15)
	private String paymentType;
	@Column(name="card_no", length = 20)
	private String cardNo;

	@JsonIgnore
	@OneToOne(mappedBy = "userPayment", cascade = CascadeType.ALL)
    private User user;
	
	public UserPayment() {
	
	}

	public UserPayment(int upId, int userId, String paymentType, String cardNo, int ccv, User user) {
		this.upId = upId;
		this.userId = userId;
		this.paymentType = paymentType;
		this.cardNo = cardNo;
		this.user = user;
	}


	public int getUpId() {
		return upId;
	}

	public void setUpId(int upId) {
		this.upId = upId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserPayment [upId=" + upId + ", userId=" + userId + ", paymentType=" + paymentType + ", cardNo="
				+ cardNo + ",  user=" + user + "]";
	}

	
}
