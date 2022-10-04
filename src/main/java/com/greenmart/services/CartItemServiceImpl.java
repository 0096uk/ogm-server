package com.greenmart.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmart.daos.CartItemDao;
import com.greenmart.dtos.CartItemDTO;
@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
@Autowired
private CartItemDao cartItemDao;
	@Override
	public void deleteFromCartItem(CartItemDTO dto) {
	cartItemDao.deleteById(dto.getProductId());
	
	}

}
