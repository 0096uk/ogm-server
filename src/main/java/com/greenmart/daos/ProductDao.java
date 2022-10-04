package com.greenmart.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.greenmart.pojos.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	//Product findByProductId(int id);

	Product findByName(String name);
	
	List<Product> findAll();
	List<Product> findAllByName(String name);
//	@Query("select * from Product p where p.productName=name ")
//	List<Product> findByProductNameMultiple(String name);
	
	boolean existsByName(String name);
	boolean existsByProductId(int id);
	
	boolean deleteByProductId(int id);
	boolean deleteByName(String name);
	
	List<Product> findByCategoryAndStatus(String category, int status);

	List<Product> findByNameContaining(String name);
	
	Product findByNameAndSupplierSupplierId( String name , int supplierId );
	@Transactional
	@Modifying
	@Query(value="update product p set p.status=?2 where p.product_id=?1",nativeQuery=true)
	void updateStatus(int product_id,int status);
	
	List<Product> findAllByStatus(int status);

	List<Product> findByCategory(String category);
}
