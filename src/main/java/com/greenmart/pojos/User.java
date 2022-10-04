package com.greenmart.pojos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.Set;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	@Column(length = 30, unique = true, nullable = false)
	private String email;
	@Column(length = 100, nullable = false)
	private String password;
	@Column(length = 15, unique = true, nullable = false)
	private String phone;
	@Column(name = "first_name", length = 10, nullable = false)
	private String firstName;
	@Column(name = "last_name", length = 10, nullable = false)
	private String lastName;
	@Column(name = "address", length = 200)
	private String address;
	@Column(name = "city", length = 20)
	private String city;
	@Column(name = "pincode")
	private int pincode;
	@Column(name = "country", length = 20)
	private String country;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "created_at")
	private Date createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	
	@Transient
	@Nullable
	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	//@JsonIgnore ////////// bcoz recusrion in add address API
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Order> order_list;

	//@JsonIgnore // bcoz recursion during adding to cart before sign out
	@OneToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id"  )
	private UserPayment userPayment;
	
	//@JsonIgnore
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
	private Cart cart;

	public User() {
	}

	public User(int userId, String email, String password, String phone, String firstName, String lastName,
			String address, String city, int pincode, String country, Date createdAt, Date modifiedAt,
			List<Order> order_list, UserPayment userPayment, Cart cart) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.country = country;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.order_list = order_list;
		this.userPayment = userPayment;
		this.cart = cart;
	}

//
//	public User(int userId, String email, String password, String phone, String firstName, String lastName,
//			Date createdAt, Date modifiedAt, List<Order> order_list,  UserPayment userPayment,
//			Cart cart) {
//		super();
//		this.userId = userId;
//		this.email = email;
//		this.password = password;
//		this.phone = phone;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.createdAt = createdAt;
//		this.modifiedAt = modifiedAt;
//		this.order_list = order_list;
//		this.userPayment = userPayment;
//		this.cart = cart;
//	}

	/*
	 * public List<Order> getOrder_list() { return order_list; }
	 * 
	 * public void setOrder_list(List<Order> order_list) { this.order_list =
	 * order_list; }
	 */

	public UserPayment getUserPayment() {
		return userPayment;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public List<Order> getOrder_list() {
		return order_list;
	}

	public void setOrder_list(List<Order> order_list) {
		this.order_list = order_list;
	}

	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", phone=" + phone
//				+ ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
//				+ ", pincode=" + pincode + ", country=" + country + ", createdAt=" + createdAt + ", modifiedAt="
//				+ modifiedAt + ", order_list=" + order_list + ", userPayment=" + userPayment + ", cart=" + cart + "]";
//	}

}
