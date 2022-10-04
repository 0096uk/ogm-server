package com.greenmart.services;

import java.util.List;

import com.greenmart.dtos.OrderDTO;
import com.greenmart.dtos.OrderDTO1;
import com.greenmart.pojos.Order;

public interface OrderService {
	
      Order saveOrder(Order o);
      
      Order saveOrderFromDto( OrderDTO dto );
      void updateStatus(int order_id,int status);

}
