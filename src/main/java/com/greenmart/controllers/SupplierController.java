package com.greenmart.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmart.configs.JwtTokenHelper;
import com.greenmart.dtos.ChangePassword;
import com.greenmart.dtos.ProductDTO;
import com.greenmart.dtos.Response;
import com.greenmart.pojos.Employee;
import com.greenmart.pojos.Product;
import com.greenmart.pojos.Supplier;
import com.greenmart.pojos.User;
import com.greenmart.services.ProductService;
import com.greenmart.services.SupplierService;


@CrossOrigin
@RequestMapping("/supplier")
@RestController
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@PostMapping("/authenticate")
	public ResponseEntity<?> supplierAuthenticate(@RequestBody  Supplier supplier) {
		System.out.println("in supplier authenticate ctr");
		System.out.println(supplier);
		Supplier supplier2 = supplierService.authenticate(supplier.getEmail(), supplier.getPassword());
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(supplier2 != null)
		{
			result.put("status", "success");
		}
		else
		{
			result.put("status", "error");
		}
		result.put("data", supplier2);
		return ResponseEntity.ok(result);
	}

//	@PostMapping("/signup")
//	public ResponseEntity<?> supplierSignUp(@RequestBody Supplier supplier) {
//		Supplier supplier2 = supplierService.saveSupplier(supplier);
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		result.put("status", "success");
//		result.put("data", supplier2);
//		return ResponseEntity.ok(result);
//
//	}
	@PostMapping("/signup")
	public ResponseEntity<?> supplierSignup(Supplier supplier){
		System.out.println("in supplier sign up controller");
		String newpass = bcryptEncoder.encode(supplier.getPassword());
		supplier.setPassword(newpass);
		Supplier supplier1 = supplierService.saveSupplier(supplier);
		System.out.println(supplier1);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("data",supplier1);
		return ResponseEntity.ok(result);		
	}

//	@PutMapping("/updatepassword/{id}")
//	public ResponseEntity<?> changePassword(@RequestBody Supplier supplier, @PathVariable("id") int id) {
//		Supplier supplier2 = supplierService.updatePassword(supplier, id);
//		supplier2.setPassword(supplier.getPassword());
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		result.put("status", "success");
//		result.put("data", supplier2);
//		return ResponseEntity.ok(result);
//
//	}
	
	@PutMapping("/updatepassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword dto ) {
		System.out.println("supplier change password controller");
		System.out.println("incoming body "+dto);
	Supplier supplier = supplierService.updatePassword(dto.getEmail() , dto.getCurrentPassword(), dto.getNewPassword());
	System.out.println(supplier);
	if(supplier != null)
		return Response.success(supplier);
	else
		return Response.error(supplier);
	}

	@GetMapping("")
	public ResponseEntity<List<Supplier>> findAllSupplier() {
		List<Supplier> list = supplierService.findAll();
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteSupplier(@PathVariable("id") int id) {
		boolean success = supplierService.deleteSupplier(id);
		return ResponseEntity.ok(success);
	}
	
	@GetMapping("/getproducts")
	public ResponseEntity<?> findAllProducts(){
		
		List<Product> list = productService.findAllProducts();
		Stream<ProductDTO> result =list.stream().map(products->ProductDTO.fromEntity(products));
		return Response.success(result);
	}
	
	@GetMapping("/allsuppliers")
	public ResponseEntity<?> getAllSuppliers(){
		List<Supplier> list = supplierService.findAll();
		return Response.success(list);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteThisSupplier(@PathVariable int id){
		supplierService.deleteThisSupplier(id);
		return Response.success("success");
		
	}
  
}
