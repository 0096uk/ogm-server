package com.greenmart.dtos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenmart.pojos.User;
import com.greenmart.pojos.UserPayment;

public class UserPaymentDTO {
	private int upId;
	private int userId;
	private String paymentType;
	private String cardNo;
	@JsonProperty
	private User user;

	public UserPaymentDTO() {

	}

	public UserPaymentDTO(int upId, int userId, String paymentType, String cardNo, User user) {
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
		return "UserPaymentDTO [upId=" + upId + ", userId=" + userId + ", paymentType=" + paymentType + ", cardNo="
				+ cardNo + ", user=" + user + "]";
	}

	public static UserPaymentDTO fromEntity(UserPayment entity) {
		UserPaymentDTO dto = new UserPaymentDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;

	}

	public static UserPayment toentities(UserPaymentDTO dto) {
		UserPayment payment = new UserPayment();
		BeanUtils.copyProperties(dto, payment);
		return payment;
	}
}
