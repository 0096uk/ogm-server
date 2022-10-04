package com.greenmart.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.OrderItem;
import com.greenmart.pojos.User;

public class OrderDTO1 {
	private int userId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int pincode;
	private String country;
	private String phone;
	private String email;

	private int OrderId;
	private double total;
	private int status;
	private Date deliveryDate;
	private List<OrderItemDTO1> orderItemDtoList;

	// private UserDTO userDto;

	public OrderDTO1() {
		// TODO Auto-generated constructor stub
	}

	public OrderDTO1(int orderId, double total, int status, List<OrderItemDTO1> orderItemDtoList) {
		OrderId = orderId;
		this.total = total;
		this.status = status;
		this.orderItemDtoList = orderItemDtoList;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<OrderItemDTO1> getOrderItemDtoList() {
		return orderItemDtoList;
	}

	public void setOrderItemDtoList(List<OrderItemDTO1> orderItemDtoList) {
		this.orderItemDtoList = orderItemDtoList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

//public UserDTO getUserDto() {
//	return userDto;
//}
//public void setUserDto(UserDTO userDto) {
//	this.userDto = userDto;
//}

	@Override
	public String toString() {
		return "OrderDTO1 [OrderId=" + OrderId + ", total=" + total + ", status=" + status + ", orderItemDtoList="
				+ orderItemDtoList + "]";
	}

	public static OrderDTO1 fromEntity(Order order) {
		OrderDTO1 orderDto = new OrderDTO1();
		BeanUtils.copyProperties(order, orderDto);
		User user = order.getUser();
		orderDto.setUserId(user.getUserId());

		ArrayList<OrderItemDTO1> orderItemDtoList1 = new ArrayList<OrderItemDTO1>();
		for (OrderItem orderItem : order.getOrderItemList()) {

			OrderItemDTO1 orderItemDto = OrderItemDTO1.fromEntity(orderItem);
			orderItemDtoList1.add(orderItemDto);
		}

		orderDto.setOrderItemDtoList(orderItemDtoList1);

		return orderDto;

	}

	public static OrderDTO1 fromEntityforEmployee(Order order) {
		User user = order.getUser();
		OrderDTO1 orderDto = new OrderDTO1();
		BeanUtils.copyProperties(order, orderDto);
		BeanUtils.copyProperties(user, orderDto);

		// orderDto.setUserDto(UserDTO.fromEntity(user));

		ArrayList<OrderItemDTO1> orderItemDtoList1 = new ArrayList<OrderItemDTO1>();
		for (OrderItem orderItem : order.getOrderItemList()) {

			OrderItemDTO1 orderItemDto = OrderItemDTO1.fromEntity(orderItem);
			orderItemDtoList1.add(orderItemDto);
		}

		orderDto.setOrderItemDtoList(orderItemDtoList1);

		return orderDto;

	}

}
