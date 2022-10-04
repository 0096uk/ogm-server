package com.greenmart.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greenmart.daos.ProductDao;
import com.greenmart.daos.SupplierDao;
import com.greenmart.dtos.ProductDTO;
import com.greenmart.pojos.Product;
import com.greenmart.utils.StorageService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SupplierDao supplierDao;

	@Autowired
	private StorageService storageService;

	@Override
	public Product findByProductId(int id) {
		// TODO Auto-generated method stub
		// return productDao.findByProductId(id);
		return null;
	}

	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return productDao.findByName(name);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	@Override
	public List<Product> findAllByProductName(String name) {
		// TODO Auto-generated method stub
		return productDao.findAllByName(name);
	}

	@Override
	public boolean existsByName(String name) {
		// TODO Auto-generated method stub
		return productDao.existsByName(name);

	}

	@Override
	public boolean existsByProductId(int id) {
		// TODO Auto-generated method stub
		return productDao.existsByProductId(id);
	}

	@Override
	public boolean deleteByProductId(int id) {
		// TODO Auto-generated method stub
		if (productDao.existsByProductId(id)) {
			productDao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteByName(String name) {
		// TODO Auto-generated method stub
		if (productDao.existsByName(name)) {
			productDao.deleteByName(name);
			return true;
		}
		return false;
	}

	@Override
	public Product save(Product p) {
		// TODO Auto-generated method stub
		p.setCreatedAt(new Date());
		p.setModifiedAt(new Date());
		return productDao.save(p);
	}

	@Override
	public Product saveWithImage(ProductDTO productDto, MultipartFile productImage) {
		System.out.println( productDto);
		Product product = productDao.findByNameAndSupplierSupplierId(productDto.getName(),
				productDto.getSupplierId());
		if (product == null) {
			System.out.println( "Inside 1") ;
			product = ProductDTO.toEntity(productDto);
			String fileName = storageService.store(productImage);
			product.setCreatedAt(new Date());
			product.setModifiedAt(new Date());
			product.setThumbnail(fileName);
			product.setSupplier(supplierDao.getById(productDto.getSupplierId()));
			System.out.println(productDto.getCategory());
			product.setCategory(productDto.getCategory());
		} else {
			//System.out.println( product );
			System.out.println( "inside 2") ;
			product.setSupplier(supplierDao.getById(productDto.getSupplierId()));
			product.setInitialQuantity(product.getInitialQuantity() + productDto.getInitialQuantity());
			product.setPresentQuantity(product.getPresentQuantity() + productDto.getInitialQuantity());
		}
		return productDao.saveAndFlush(product);
	}

	@Override
	public List<Product> findAllProducts() {
		int status = 1;
		return productDao.findAllByStatus(status);
	}

	@Override
	public List<Product> getAllFruits() {
		System.out.println("inside get all fruits services");
		String fruits = "fruits";
		int status = 1;
		List<Product> list = productDao.findByCategoryAndStatus(fruits,status);
		return list;
	}

	@Override
	public List<Product> getAllVegetables() {
		System.out.println("inside get all vegetables services");
		String vegetables = "vegetables";
		int status = 1;
		List<Product> list = productDao.findByCategoryAndStatus(vegetables,status);
		return list;
	}

	@Override
	public List<Product> findProductLikeName(String name) {

		return productDao.findByNameContaining(name);
	}
	@Override
	public void updateStatus(int product_id, int status) {
		productDao.updateStatus(product_id, status);
	}

	@Override
	public void deleteThisProduct(int id) {
		productDao.deleteById(id);
		
	}

	@Override
	public List<Product> getAllFruitsAdmin() {
		System.out.println("inside get all fruits services");
		String fruits = "fruits";
		
		List<Product> list = productDao.findByCategory(fruits);
		return list;
	
	}

	@Override
	public List<Product> getAllVegetablesAdmin() {
		System.out.println("inside get all vegetables services");
		String vegetables = "vegetables";
		
		List<Product> list = productDao.findByCategory(vegetables);
		return list;
	}
	
	
}
