package com.greenmart.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greenmart.daos.EmployeeDao;
import com.greenmart.daos.OrderDao;
import com.greenmart.pojos.Employee;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.Supplier;
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
@Autowired
private EmployeeDao employeeDao;
@Autowired
private OrderDao orderDao;

@Autowired
private PasswordEncoder bcryEncoder;

	@Override
	public Employee getByEmail(String email) {
		Employee employee = employeeDao.findByEmail(email);
		return employee;
	}
	@Override
	public Employee authenticate(String email, String password) {
		Employee employee = getByEmail(email);
		if(bcryEncoder.matches(password, employee.getPassword()))
			return employee;
		else
			return null;
	}
	@Override
	public Employee saveEmp(Employee emp) {
		emp.setCreatedAt(new Date());
		emp.setModifiedAt(new Date());
		Employee emp1 = employeeDao.save(emp);
		System.out.println(emp1);
		
		return emp1;
	}
//	@Override
//	public Employee updatePassword(Employee emp, int id) {
//		Employee emp1 = employeeDao.findById(id).get();
//		emp1.setPassword(emp.getPassword());
//		return emp1;
//	}
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		List<Employee> list = employeeDao.findAll();
		return list;
	}
	@Override
	public List<Order> findAllOrdersByEmployee(int id ) {
		//return orderDao.findOrderByEmployeeEmpId(id);
		return orderDao.findOrderByEmployeeEmpIdAndStatus(id , 0);
	}
	@Override
	public Employee updatePassword(String email, String currentPassword, String newPassword) {
		System.out.println(email + currentPassword + newPassword);
	 Employee employee = employeeDao.findByEmail(email);
	 if( bcryEncoder.matches(currentPassword, employee.getPassword()))
	 {
		 String newpass = bcryEncoder.encode(newPassword);
		 employee.setPassword(newpass);
	 }
	 else	
			return null;
	 employee = employeeDao.save(employee);
		return employee;
	}
//	@Override
//	public Supplier updatePassword(String email, String currentPassword, String newPassword) {
//		System.out.println(email + currentPassword + newPassword);
//		Supplier supplier = supplierDao.findByEmail(email);
//		if( supplier.getPassword().equals(currentPassword))
//			supplier.setPassword(newPassword);
//		else	
//			return null;
//		supplier = supplierDao.save(supplier);
//		return supplier;
//	}
	@Override
	public void deleteEmployee(int id) {
		employeeDao.deleteById(id);
		
	}
	

}
