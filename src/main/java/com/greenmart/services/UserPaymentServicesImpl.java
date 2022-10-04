package com.greenmart.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmart.daos.UserPaymentDao;
import com.greenmart.pojos.UserPayment;
@Transactional
@Service
public class UserPaymentServicesImpl implements UserPaymentServices  {
	
	@Autowired
	UserPaymentDao userPaymentDao;

	@Override
	public UserPayment savePayment(UserPayment upayment ) {
		UserPayment upay= userPaymentDao.save(upayment);
		return upay;
	}

	@Override
	public UserPayment getUserPaymentByUserId(int userId) {
		System.out.println("inside service layer of user payment");
	 UserPayment t = userPaymentDao.getUserPaymentByUserId(userId);
		System.out.println(t);
		return t;
	
	}

	

}
