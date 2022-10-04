package com.greenmart.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderDTO {

	private int userId;
	private double total;
	private List<OrderItemDTO> order_item;
	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}
	public OrderDTO(int userId, double total, List<OrderItemDTO> order_item) {
		this.userId = userId;
		this.total = total;
		this.order_item = order_item;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<OrderItemDTO> getOrder_item() {
		return order_item;
	}
	public void setOrder_item(List<OrderItemDTO> order_item) {
		this.order_item = order_item;
	}
	@Override
	public String toString() {
		return "OrderDTO [userId=" + userId + ", total=" + total + ", order_item=" + order_item + "]";
	}
	
}
