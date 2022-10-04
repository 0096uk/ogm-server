package com.greenmart.services;

import java.util.Date;
import java.util.List;

import com.greenmart.pojos.Employee;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.Supplier;

public interface EmployeeService {
	Employee getByEmail(String email);

	Employee authenticate(String email, String password);

	Employee saveEmp(Employee emp);

	Employee updatePassword(String email, String currentPassword , String newPassword);

	List<Employee> findAll();
	
	//List<Order> findEmployeeOrders( int id );
	
	List<Order> findAllOrdersByEmployee( int id ) ;

	void deleteEmployee(int id);
}
