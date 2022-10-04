package com.greenmart.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.greenmart.pojos.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
	List<Order> findOrderByEmployeeEmpId(int emp_id) ;
	List<Order> findOrderByUserUserId( int user_id ) ;
	
	List<Order> findOrderByEmployeeEmpIdAndStatus( int emp_id , int status);
	@Transactional
	@Modifying
	@Query(value="update orders o set o.status=?2 where o.order_id=?1",nativeQuery=true)
	void updateStatus(int order_id,int status);
}
