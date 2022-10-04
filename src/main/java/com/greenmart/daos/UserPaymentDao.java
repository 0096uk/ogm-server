package com.greenmart.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenmart.pojos.UserPayment;

public interface UserPaymentDao extends JpaRepository<UserPayment,Integer> {
	
//	@Query(value="select * from userpayment u where u.user_id=user_id ",nativeQuery = true)
	UserPayment getUserPaymentByUserId(int userId);

}
