package com.greenmart.services;


import com.greenmart.dtos.UserPaymentDTO;
import com.greenmart.pojos.UserPayment;

public interface UserPaymentServices  {
	UserPayment savePayment(UserPayment userPayment);

	UserPayment getUserPaymentByUserId(int userId);

}
