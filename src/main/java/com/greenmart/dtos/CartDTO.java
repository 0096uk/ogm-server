package com.greenmart.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenmart.pojos.Cart;
import com.greenmart.pojos.CartItem;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.OrderItem;
import com.greenmart.pojos.User;

public class CartDTO {
	private int cartId;
	private int userId;
	private double total;
	private List<CartItemDTO1> cartItemDtoList;;
	private List<CartItemDTO> cItem;

	public CartDTO() {

	}

	public CartDTO(int cartId, int userId, double total, List<CartItemDTO1> cartItemDtoList, List<CartItemDTO> cItem) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.total = total;
		this.cartItemDtoList = cartItemDtoList;
		this.cItem = cItem;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

	public List<CartItemDTO> getcItem() {
		return cItem;
	}

	public void setcItem(List<CartItemDTO> cItem) {
		this.cItem = cItem;
	}

	public List<CartItemDTO1> getCartItemDtoList() {
		return cartItemDtoList;
	}

	public void setCartItemDtoList(List<CartItemDTO1> cartItemDtoList) {
		this.cartItemDtoList = cartItemDtoList;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", userId=" + userId + ", total=" + total + ", cartItemDtoList="
				+ cartItemDtoList + ", cItem=" + cItem + "]";
	}

	public static CartDTO fromEntity(Cart cart) {
		CartDTO cartDto = new CartDTO();
		BeanUtils.copyProperties(cart, cartDto);

		ArrayList<CartItemDTO1> cartItemDtoList1 = new ArrayList<CartItemDTO1>();
		for (CartItem cartItem : cart.getcItem()) {

			CartItemDTO1 cartItemDto = CartItemDTO1.fromEntity(cartItem);
			cartItemDtoList1.add(cartItemDto);
		}

		cartDto.setCartItemDtoList(cartItemDtoList1);

		return cartDto;

	}

	public static Cart toEntity(CartDTO dto) {
		System.out.println("inside carttoentity");
		Cart cart = new Cart();
		BeanUtils.copyProperties(dto, cart);
		return cart;
	}

}
