package com.greenmart.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenmart.dtos.ProductDTO;
import com.greenmart.dtos.ProductImageDTO;
import com.greenmart.dtos.Response;
import com.greenmart.pojos.Product;
import com.greenmart.services.ProductService;

@CrossOrigin
@RequestMapping("/products")
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("")
	public ResponseEntity<?> findAllProducts() {
		List<Product> productList = productService.findAllProducts();
		System.out.println(productList);
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		for (Product product : productList)
			result.add(ProductDTO.fromEntity(product));
		Collections.shuffle(result);
		return Response.success(result);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> findProductById(@PathVariable("id") int id) {

		Product product = productService.findByProductId(id);
//   		ProductDTO productItemDto = ProductDTO.	
		return ResponseEntity.ok(product);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> findProductByName(@PathVariable("name") String name) {
		System.out.println("in find by name");
		Product product = productService.findByName(name);
		System.out.println(product);
//   		ProductDTO productItemDto = ProductDTO.	
		return ResponseEntity.ok(product);
	}

	@PostMapping("/add")
	public ResponseEntity<?> saveProduct(ProductDTO dto) throws URISyntaxException {
		/*Product product = ProductDTO.toEntity(dto);
		System.out.println(product);*/

		Product newProduct = productService.saveWithImage(dto, dto.getThumbnail());
	//	ProductDTO productDto = ProductDTO.fromEntity(newProduct) ;
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (newProduct != null) {
			result.put("status", "success");
			return ResponseEntity.ok(result);
		}

		result.put("status", "error");
		return ResponseEntity.ok(result);
	}

	/*@PostMapping("image")
	public ResponseEntity<?> save(ProductImageDTO productDto) {
		System.out.println("in save ctr incoming obj =" + productDto);
		Product product = ProductImageDTO.toEntity(productDto);
		System.out.println("in save image ctr =" + product);
		product = productService.saveWithImage(product, productDto.getThumbnail());
		return ResponseEntity.ok(product);
	}*/

	@GetMapping("/fruits")
	public ResponseEntity<?> getAllFruits(){
	System.out.println("in fruits controller");
		List<Product> list = productService.getAllFruits();
		System.out.println(list.toString());
		List<ProductDTO> fruitList = new ArrayList<ProductDTO>();

		for (Product fruit : list) {
			fruitList.add(ProductDTO.fromEntity(fruit));
		}
		Collections.shuffle(fruitList);
		return Response.success(fruitList);
	}

	@GetMapping("/vegetables")
	public ResponseEntity<?> getAllVegetables(){
		System.out.println("inside vegetables controller");
		List<Product> list = productService.getAllVegetables();
		List<ProductDTO> vegetableList = new ArrayList<ProductDTO>();
		for (Product fruit : list) {
			vegetableList.add(ProductDTO.fromEntity(fruit));
		}
		Collections.shuffle(vegetableList);
		return Response.success(vegetableList);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findProductLikeName(@RequestParam(name="q",required=false) String query ){
          if(query==null)
        	  query="";
          List<Product> list = productService.findProductLikeName(query);
          Stream<ProductDTO> result = list.stream().map(product->ProductDTO.fromEntity(product));
          return Response.success(result);
	
	}
	
	@PutMapping("/updatestatus/{product_id}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable int product_id,@PathVariable int status) {
		productService.updateStatus(product_id, status);
		return Response.success("success");
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		productService.deleteThisProduct(id);
		return Response.success("success");
	}
	
	@GetMapping("/admin/fruits")
	public ResponseEntity<?> getAllFruitsAdmin(){
	System.out.println("in fruits controller");
		List<Product> list = productService.getAllFruitsAdmin();
		System.out.println(list.toString());
		List<ProductDTO> fruitList = new ArrayList<ProductDTO>();

		for (Product fruit : list) {
			fruitList.add(ProductDTO.fromEntity(fruit));
		}
		return Response.success(fruitList);
	}

	@GetMapping("/admin/vegetables")
	public ResponseEntity<?> getAllVegetablesAdmin(){
		System.out.println("inside vegetables controller");
		List<Product> list = productService.getAllVegetablesAdmin();
		List<ProductDTO> vegetableList = new ArrayList<ProductDTO>();
		for (Product fruit : list) {
			vegetableList.add(ProductDTO.fromEntity(fruit));
		}
		return Response.success(vegetableList);
	}
}
