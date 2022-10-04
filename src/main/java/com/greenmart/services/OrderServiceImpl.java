package com.greenmart.services;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmart.daos.EmployeeDao;
import com.greenmart.daos.OrderDao;
import com.greenmart.daos.OrderItemDao;
import com.greenmart.daos.ProductDao;
import com.greenmart.daos.UserDao;
import com.greenmart.dtos.OrderDTO;
import com.greenmart.dtos.OrderItemDTO;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.OrderItem;
import com.greenmart.pojos.Product;
import com.greenmart.pojos.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
 
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public Order saveOrder(Order o) {
		o.setCreatedAt(new Date());
		o.setModifiedAt(new Date());
		return orderDao.save(o);
	}

	@Override
	public Order saveOrderFromDto(OrderDTO dto) {
		Order order = new Order();
		order.setTotal(dto.getTotal());
		User user = userDao.findById(dto.getUserId()).get();
		// Similary add random employee and up

		order.setUser(user);
		order.setCreatedAt(new Date());
		order.setModifiedAt(new Date());
		Date dt = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
		order.setDeliveryDate(dt);

		//sorder = orderDao.save(order);

		for (OrderItemDTO orderItemDto : dto.getOrder_item()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);

			Product product = productDao.findById(orderItemDto.getProductId()).orElseThrow(null);
			
			
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			// concurrent order handling code
			if(orderItemDto.getQuantity() > product.getPresentQuantity())
			{
				return null;
			}
				
			
			product.setPresentQuantity(product.getPresentQuantity() - orderItemDto.getQuantity());
			
			
			
			orderItem.setProduct(product);

			orderItem.setQuantity(orderItemDto.getQuantity());

			orderItem = orderItemDao.save(orderItem);
			
			productDao.saveAndFlush(product);
			

		}
		order.setEmployee(employeeDao.getRandomEmployee());
		order = orderDao.save(order);
		
		

		return order;
	}

	@Override
	public void updateStatus(int order_id, int status) {

		orderDao.updateStatus(order_id, status);

	}
}
