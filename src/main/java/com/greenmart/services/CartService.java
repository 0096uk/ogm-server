package com.greenmart.services;

import com.greenmart.dtos.CartDTO;
import com.greenmart.pojos.Cart;

public interface CartService {
Cart saveCart(Cart cart);
Cart saveCartFromDto(CartDTO dto);
Cart findCartByUserId(int id);
void deleteFromCart(int id);

}
