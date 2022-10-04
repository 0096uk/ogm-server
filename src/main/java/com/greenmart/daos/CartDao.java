package com.greenmart.daos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.greenmart.pojos.Cart;


public interface CartDao extends JpaRepository<Cart, Integer> {
//	@Query(value = "delete from Cart c where c.user_id=?1", nativeQuery = true)
   Cart findCartByUserUserId(int id);	
   @Transactional
	@Modifying
	@Query(value = "delete from cart c where c.cart_id=?1", nativeQuery = true)
	   void delcart(int id);	
}
