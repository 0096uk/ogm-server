package com.greenmart.dtos;

import org.springframework.beans.BeanUtils;

import com.greenmart.pojos.CartItem;

public class CartItemDTO {
	private int ciId;
	private int productId;
	private int quantity;
	
	
	public CartItemDTO() {
	}


	public CartItemDTO(int ciId, int productId, int quantity) {
		this.ciId = ciId;
		this.productId = productId;
		this.quantity = quantity;
	}


	public int getCiId() {
		return ciId;
	}


	public void setCiId(int ciId) {
		this.ciId = ciId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}





	@Override
	public String toString() {
		return "CartItemDTO [ciId=" + ciId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	/*public static CartItemDTO fromEntity(CartItem entity) {
		CartItemDTO dto = new CartItemDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	public static CartItem toEntity(CartItemDTO itemdto) {
		CartItem item = new CartItem();
		BeanUtils.copyProperties(itemdto, item);
		return item;
	}*/
}
