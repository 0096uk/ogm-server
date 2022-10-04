package com.greenmart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmart.daos.OrderItemDao;
import com.greenmart.daos.ProductDao1;
import com.greenmart.daos.UserDao;
import com.greenmart.daos.UserDao1;
import com.greenmart.dtos.OrderDTO;
import com.greenmart.dtos.OrderDTO1;
import com.greenmart.dtos.OrderItemDTO;
import com.greenmart.dtos.Response;
import com.greenmart.dtos.UserDTO;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.OrderItem;
import com.greenmart.pojos.Product;
import com.greenmart.pojos.User;
import com.greenmart.services.OrderService;
import com.greenmart.services.ProductService;

@CrossOrigin
@RequestMapping("/orders")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/addorder")
	private ResponseEntity<?> saveOrder(@RequestBody OrderDTO dto) {
		/*
		 * System.out.println("inside add order controller"); ArrayList<OrderItem>
		 * orderItemList = new ArrayList<OrderItem>();
		 * System.out.println("incoming body"+dto); Order order = OrderDTO.toEntity(o);
		 * Order order = new Order(); BeanUtils.copyProperties(dto, order); User user =
		 * userDao.findById(dto.getUserId()).get(); order.setUser(user);
		 * System.out.println(order); // order.setOrderItemList(dto.getOrder_item());
		 * for (OrderItemDTO orderItemDto : dto.getOrder_item()) { OrderItem orderItem =
		 * new OrderItem() ; //Product product =
		 * productService.findByProductId(orderItemDto.getProductId()); Product product
		 * = productDao1.getById(orderItemDto.getProductId());
		 * orderItem.setProduct(product);
		 * orderItem.setQuantity(orderItemDto.getQuantity()); orderItem =
		 * orderItemDao.save(orderItem); orderItemList.add(orderItem);
		 * 
		 * } order.setOrderItemList(orderItemList);
		 */

		Order order = orderService.saveOrderFromDto(dto);
		System.out.println(order);
		if( order != null )
		return Response.success("Success");
		else
			return Response.error("Something went wrong");

	}
//	@DeleteMapping("/deleteOrder")
//	private ResponseEntity<?> deleteOrder(@RequestBody OrderDTO dto) {
//		
//	}
	
	


}
