package com.greenmart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greenmart.daos.SupplierDao;
import com.greenmart.daos.UserDao1;
import com.greenmart.pojos.Supplier;
import com.greenmart.pojos.User;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserDao1 userRepo;
	
	@Autowired
	private SupplierDao supplierRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("user not found"));
		
		return user;
	}

	
//	public UserDetails loadUserByUsername(String username,String type) throws UsernameNotFoundException {
//
//		System.out.println("calling custom supplier service");
//		
//		Supplier supplier = this.supplierRepo.findByEmail(username);
//		
//		return supplier;
//	}
}
