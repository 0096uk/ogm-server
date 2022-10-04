package com.greenmart.pojos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee")
public class Employee extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id")
	private int empId;
	@Column(length = 30,unique = true,nullable=false)
	private String email;
	@Column(length = 100,nullable = false)
	private String password;
	@Column(length = 15 , unique = true, nullable = false)
	private String phone;
	@Column(name="first_name",length = 10, nullable = false)
	private String firstName;
	@Column(name="last_name",length = 10, nullable = false)
	private String lastName;
	@Column(length = 10)
	private String role;
	
	@Nullable
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "created_at")
	private Date createdAt;
	
	@Nullable
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee",orphanRemoval = true)
	private List<Order> orderList;

	public Employee() {
	}

	public Employee(int empId, String email, String password, String phone, String firstName, String lastName, String role,
			Date createdAt, Date modifiedAt, List<Order> orderList) {
		this.empId = empId;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.orderList = orderList;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", orderList=" + orderList + "]";
	}

}
