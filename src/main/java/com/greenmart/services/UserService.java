package com.greenmart.services;

import java.util.List;

import com.greenmart.dtos.UserDTO;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.User;

public interface UserService {
	User getByEmail(String email);
	User getById(int id);


	User authenticate(String email, String password);

	User saveUser(User user);

	User updatePassword(String email, String currentPassword , String newPassword);
	User editUserFromDTO(UserDTO dto);
	List<Order> findAllOrdersByUser(int id);
	
	List<User> getAllUsers();
	void deleteUser(int id);
}
