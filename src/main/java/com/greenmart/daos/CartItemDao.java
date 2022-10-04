package com.greenmart.daos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.greenmart.pojos.Cart;
import com.greenmart.pojos.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer>{
	@Transactional
	@Modifying
	@Query(value = "delete from cart_item c where c.cart_id=?1", nativeQuery = true)
	void delByCartid(int id);
}
