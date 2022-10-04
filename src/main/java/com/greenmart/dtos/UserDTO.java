package com.greenmart.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenmart.pojos.Cart;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.User;
import com.greenmart.pojos.UserPayment;

public class UserDTO {
	private int userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String city;
	private int pincode;
	private String country;
	private String jwtToken;
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public UserDTO(int userId, String firstName, String lastName, String password, String email, String phone,
			String address, String city, int pincode, String country, String jwtToken, UserPaymentDTO userPaymentDto,
			List<OrderDTO1> orderDtoList, CartDTO cartDto) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.country = country;
		this.jwtToken = jwtToken;
		this.userPaymentDto = userPaymentDto;
		this.orderDtoList = orderDtoList;
		this.cartDto = cartDto;
	}

	@JsonIgnore // add cart completed the while sign in recursion
	private UserPaymentDTO userPaymentDto;
	//@JsonIgnore
	private List<OrderDTO1> orderDtoList;
	private CartDTO cartDto;

	public UserDTO() {
	}

	public UserDTO(int userId, String firstName, String lastName, String password, String email, String phone,
			String address, String city, int pincode, String country, UserPaymentDTO userPaymentDto,
			List<OrderDTO1> orderDtoList, CartDTO cartDto) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.country = country;
		this.userPaymentDto = userPaymentDto;
		this.orderDtoList = orderDtoList;
		this.cartDto = cartDto;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserPaymentDTO getUserPaymentDto() {
		return userPaymentDto;
	}

	public void setUserPaymentDto(UserPaymentDTO userPaymentDto) {
		this.userPaymentDto = userPaymentDto;
	}

	public List<OrderDTO1> getOrderDtoList() {
		return orderDtoList;
	}

	public void setOrderDtoList(List<OrderDTO1> orderDtoList) {
		this.orderDtoList = orderDtoList;
	}

	public CartDTO getCartDto() {
		return cartDto;
	}

	public void setCartDto(CartDTO cartDto) {
		this.cartDto = cartDto;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", phone=" + phone + ", address=" + address + ", city=" + city
				+ ", pincode=" + pincode + ", country=" + country + ", userPaymentDto=" + userPaymentDto
				+ ", orderDtoList=" + orderDtoList + ", cartDto=" + cartDto + "]";
	}

	public static User toEntity(UserDTO dto) {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		return user;
	}

//	public static UserDTO fromEntity(User user) {
//		UserDTO userDto = new UserDTO();
//		BeanUtils.copyProperties(user , userDto );
//		return userDto;
//	}
	public static User toEntityForAddAddress(UserDTO dto, User user) {
		user.setAddress(dto.getAddress());
		user.setPincode(dto.getPincode());
		user.setCity(dto.getCity());
		user.setCountry(dto.getCountry());
		return user;
	}

	public static UserDTO fromEntity(User user) {
		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(user, userDto);

		if (user.getUserPayment() != null) {
			UserPaymentDTO userPaymentDto = new UserPaymentDTO();
			userPaymentDto = UserPaymentDTO.fromEntity(user.getUserPayment());
			userDto.setUserPaymentDto(userPaymentDto);
		}

		// CartDTO cartDto= new CartDTO();
		if (user.getCart() != null) {
			userDto.setCartDto(CartDTO.fromEntity(user.getCart()));
		}

		if (user.getOrder_list() != null) {
			ArrayList<OrderDTO1> orderDtoList1 = new ArrayList<OrderDTO1>();

			for (Order order : user.getOrder_list()) {
				OrderDTO1 orderDto = OrderDTO1.fromEntity(order);
				orderDtoList1.add(orderDto);
			}
			userDto.setOrderDtoList(orderDtoList1);
		}

		return userDto;
	}

}
