package com.greenmart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmart.dtos.CartDTO;
import com.greenmart.dtos.CartItemDTO;
import com.greenmart.dtos.Response;
import com.greenmart.pojos.Cart;
import com.greenmart.pojos.Order;
import com.greenmart.services.CartItemService;
import com.greenmart.services.CartItemServiceImpl;
import com.greenmart.services.CartService;

@CrossOrigin
@RequestMapping("/cart")
@RestController
public class CartController {
	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@PostMapping("/addcart")
	public ResponseEntity<?> saveCart(@RequestBody CartDTO dto) {
		System.out.println("inside add cart controller");
		System.out.println(dto);
		Cart cart1 = cartService.findCartByUserId(dto.getUserId());
		System.out.println("cart======>  "+cart1);
	    if(cart1!=null) {
			cartService.deleteFromCart(cart1.getCartId());
	    }
    	System.out.println("before");
			Cart cart = cartService.saveCartFromDto(dto);
		return Response.success("Success");
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCart(@RequestBody CartDTO dto) {
		cartService.deleteFromCart(dto.getCartId());
		return Response.success("success");

	}
}
