package com.greenmart.dtos;

import org.springframework.beans.BeanUtils;

import com.greenmart.pojos.OrderItem;

public class OrderItemDTO {
	private int oiId;
	private int quantity;
	private int productId;

	public OrderItemDTO(int oiId, int quantity, int productId) {
		this.oiId = oiId;
		this.quantity = quantity;
		this.productId = productId;
	}

	public OrderItemDTO() {
	}

	public int getOiId() {
		return oiId;
	}

	public void setOiId(int oiId) {
		this.oiId = oiId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderItemDTO [oiId=" + oiId + ", quantity=" + quantity + ", productId=" + productId + "]";
	}

}
