package com.greenmart.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenmart.pojos.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);
//	UserAddress save(UserAddress userAddress);
	

}
