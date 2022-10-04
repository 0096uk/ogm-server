package com.greenmart.dtos;

import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenmart.pojos.CartItem;

public class CartItemDTO1 {
	@JsonProperty(value = "id")
	private int productId;
	private String name;
	@JsonProperty(value = "thumbnailString")
	private String thumbnail;
	private double price;
	private int quantity;

	public CartItemDTO1() {
		super();
	}

	public CartItemDTO1(int productId, String name, String thumbnail, double price, int quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.thumbnail = thumbnail;
		this.price = price;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItemDTO1 [productId=" + productId + ", Name=" + name + ", thumbnail=" + thumbnail + ", price="
				+ price + ", quantity=" + quantity + "]";
	}

	public static CartItemDTO1 fromEntity(CartItem cartItem) {
		CartItemDTO1 cartItemDto = new CartItemDTO1();
		BeanUtils.copyProperties(cartItem, cartItemDto);

		cartItemDto.setProductId(cartItem.getProduct().getProductId());
		cartItemDto.setName(cartItem.getProduct().getName());
		cartItemDto.setPrice(cartItem.getProduct().getPrice());
		cartItemDto.setThumbnail(cartItem.getProduct().getThumbnail());

		return cartItemDto;
	}

}
