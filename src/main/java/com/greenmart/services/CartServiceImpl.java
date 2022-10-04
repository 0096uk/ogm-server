package com.greenmart.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmart.daos.CartDao;
import com.greenmart.daos.CartItemDao;
import com.greenmart.daos.ProductDao;
import com.greenmart.daos.UserDao;
import com.greenmart.dtos.CartDTO;
import com.greenmart.dtos.CartItemDTO;
import com.greenmart.pojos.Cart;
import com.greenmart.pojos.CartItem;
import com.greenmart.pojos.Product;
import com.greenmart.pojos.User;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CartItemDao cartItemDao;

	@Autowired
	private CartDao cartDao;

	@Override
	public Cart saveCart(Cart cart) {
		cart.setCreatedAt(new Date());
		cart.setModifiedAt(new Date());
		return cartDao.save(cart);
		
	}

	@Override
	public Cart saveCartFromDto(CartDTO dto) {
		System.out.println("inside save cart");
		System.out.println(dto.getcItem());
		System.out.println(dto.getUserId());
		
		Cart cart = new Cart();
		cart.setTotal(dto.getTotal());
		User user = userDao.findById(dto.getUserId()).get();
		cart.setUser(user);
		cart.setCreatedAt(new Date());
		cart.setModifiedAt(new Date());
		cart= cartDao.save(cart);
		for (CartItemDTO cartItemDto : dto.getcItem()) {
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);

			Product product = productDao.findById(cartItemDto.getProductId()).get();
			cartItem.setProduct(product);
			cartItem.setQuantity(cartItemDto.getQuantity());
			cartItem = cartItemDao.save(cartItem);
		}
		return cart;
	}

	@Override
	public Cart findCartByUserId(int id) {
		System.out.println("find cart by user ID===> "+id);
		  Cart c;
		c= cartDao.findCartByUserUserId(id);
		System.out.println("finded cart====>"+c);
		return c;
	}

	@Override
	public void deleteFromCart(int id) {
		System.out.println("inside delete from cart service id= "+id);
		cartItemDao.delByCartid(id);
		cartDao.delcart(id);
		
	}

}
