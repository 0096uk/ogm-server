package com.greenmart.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenmart.pojos.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

}
