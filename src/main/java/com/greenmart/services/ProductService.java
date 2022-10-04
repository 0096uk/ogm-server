package com.greenmart.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.greenmart.dtos.ProductDTO;
import com.greenmart.pojos.Product;

public interface ProductService {
	Product findByProductId(int id);

	Product findByName(String name);
	
	List<Product> findAll();
	List<Product> findAllByProductName(String name);
//	@Query("select * from Product p where p.productName=name ")
//	List<Product> findByProductNameMultiple(String name);
	
//	@Query("select * from Product  where name=name",)
//	@Query(name ="select * from Product  where name=name",nativeQuery = true )
	boolean existsByName(String name);
	boolean existsByProductId(int id);
	
	boolean deleteByProductId(int id);
	boolean deleteByName(String name);
	
	Product save(Product p);
//	Product saveProduct(Product p, int categoryId);
	Product saveWithImage(ProductDTO productDto, MultipartFile productImage);
	
	List<Product> findAllProducts();
	List<Product> getAllFruits();
	List<Product> getAllVegetables();
	List<Product> findProductLikeName(String name);
	void updateStatus(int product_id,int status);

	void deleteThisProduct(int id);

	List<Product> getAllFruitsAdmin();

	List<Product> getAllVegetablesAdmin();
	
	
}
