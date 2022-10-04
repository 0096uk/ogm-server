package com.greenmart.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greenmart.daos.OrderDao;
import com.greenmart.daos.UserDao;
import com.greenmart.dtos.UserDTO;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.User;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired OrderDao orderDao;
	
	@Autowired
	private PasswordEncoder bcryEncoder;

	@Override
	public User getByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}
	@Override
	public User getById(int id) {
         User user = userDao.findById(id).get();
		return user;
	}

	@Override
	public User authenticate(String email, String password) {
		User user = userDao.findByEmail(email);
//		System.out.println(BCrypt.gensalt());
//		System.out.println(BCrypt.gensalt().length());
//		System.out.println( user.getPassword().substring(7,29));
//		System.out.println("****"+password);
//		System.out.println(user.getPassword());
//		
//		String newpass = BCrypt.hashpw(password, user.getPassword().substring(7,29));
		
		if (bcryEncoder.matches(password, user.getPassword()))
			return user;
		return null;
	}

	@Override
	public User saveUser(User user) {
		user.setCreatedAt(new Date());
		user.setModifiedAt(new Date());
		User user1 = userDao.save(user);
		return user1;
	}

	@Override
	public User updatePassword(String email, String currentPassword , String newPassword) {
		System.out.println(email + currentPassword + newPassword);
		User user = userDao.findByEmail(email);
		if( bcryEncoder.matches(currentPassword, user.getPassword()))
			user.setPassword(bcryEncoder.encode(newPassword));
		else	
			return null;
		user = userDao.save(user);
		return user;
	}
	@Override
	public User editUserFromDTO(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Order> findAllOrdersByUser(int id) {
		return orderDao.findOrderByUserUserId(id);
	}
	@Override
	public List<User> getAllUsers() {
		List<User> list = userDao.findAll();
		return list;
	}
	@Override
	public void deleteUser(int id) {
		userDao.deleteById(id);
		
	}
	
	

}
