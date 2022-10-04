package com.greenmart.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenmart.pojos.Employee;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmployeeDTO {
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private String role;
	@JsonIgnore
	private List<OrderDTO1> orderDtoList;

	public EmployeeDTO() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeDTO(String firstName, String lastName, String email, String password, String phone,
			List<OrderDTO1> orderDtoList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.orderDtoList = orderDtoList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<OrderDTO1> getOrderDtoList() {
		return orderDtoList;
	}

	public void setOrderDtoList(List<OrderDTO1> orderDtoList) {
		this.orderDtoList = orderDtoList;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", phone=" + phone + ", orderDtoList=" + orderDtoList + "]";
	}

	public static Employee toEntity(EmployeeDTO dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);
		return employee;
	}

	public static EmployeeDTO fromEntity(Employee employee) {
		EmployeeDTO employeeDto = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDto);

		ArrayList<OrderDTO1> orderDtoList1 = new ArrayList<OrderDTO1>();

		for (Order order : employee.getOrderList()) {
			// OrderDTO1 orderDto = OrderDTO1.fromEntity(order);
			OrderDTO1 orderDto = OrderDTO1.fromEntityforEmployee(order);
			orderDtoList1.add(orderDto);
		}

		employeeDto.setOrderDtoList(orderDtoList1);

		return employeeDto;
	}
}
