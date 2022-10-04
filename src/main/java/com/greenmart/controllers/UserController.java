package com.greenmart.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import com.greenmart.dtos.OrderDTO1;
import com.greenmart.dtos.Response;
import com.greenmart.dtos.UserDTO;
import com.greenmart.dtos.UserPaymentDTO;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.User;
import com.greenmart.pojos.UserPayment;
import com.greenmart.services.UserPaymentServices;
import com.greenmart.services.UserService;

@RequestMapping("user")
@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserPaymentServices paymentServices;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
 
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> doAuthentication(@RequestBody User user) {
	    User user1 = userService.authenticate(user.getEmail(),user.getPassword());
	    
		//this.myauthenticate(user.getEmail(), user.getPassword());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		
		final String token = jwtTokenHelper.generateToken(userDetails);
		
		user1.setJwtToken(token);
		
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (user1 != null) {
			result.put("status", "success");
			result.put("data", user1);
			return ResponseEntity.ok(result);
		}

		result.put("status", "error");
		return ResponseEntity.ok(result);
	}
	
//	private void myauthenticate(String username,String password)
//	{
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//	}

	@PostMapping("/authenticate1")
	public ResponseEntity<?> doAuthentication1(@RequestBody UserDTO userDto) {
		User user = userService.authenticate(userDto.getEmail(), userDto.getPassword());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		
		final String token = jwtTokenHelper.generateToken(userDetails);
		
		user.setJwtToken(token);

		UserDTO responseUser = UserDTO.fromEntity(user);

		HashMap<String, Object> result = new HashMap<String, Object>();
		if (responseUser != null) {
			result.put("status", "success");
			result.put("data", responseUser);
			return ResponseEntity.ok(result);
		}

		result.put("status", "error");
		return ResponseEntity.ok(result);

	}

	@PostMapping("/signup")
	public ResponseEntity<?> userSignUp( User user) {
		String newpass = bcryptEncoder.encode(user.getPassword());
		user.setPassword(newpass);
		user = userService.saveUser(user);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("data", user);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/updatepassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword dto) {
		System.out.println("user change password controller");
		System.out.println("incoming body " + dto);

		User user = userService.updatePassword(dto.getEmail(),dto.getCurrentPassword(), dto.getNewPassword());
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			result.put("status", "success");
			result.put("data", user);
			return ResponseEntity.ok(result);
		}
		result.put("status", "error");
		return ResponseEntity.ok(result);
	}

	@GetMapping("/userbyemail")
	public ResponseEntity<?> findByuserEmail(@RequestBody UserDTO user) {
		System.out.println(user.getEmail());
		User user1 = userService.getByEmail(user.getEmail());
		if (user1 == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(user1);
	}

	@PostMapping("/userbyid")
	public ResponseEntity<?> findByuserId(@RequestBody UserDTO user) {
//		System.out.println("inside user by id");
//		System.out.println(user);
		System.out.println("user id = " + user.getUserId());
		User user1 = userService.getById(user.getUserId());
//		System.out.println(user1);
		user=UserDTO.fromEntity(user1);
		System.out.println(user);

		if (user == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(user);
	}

	@PostMapping("/orders/{id}")
	public ResponseEntity<?> getUserOrders(@PathVariable("id") int id) {
		System.out.println("inside find orders by userid");
		List<Order> orderList = userService.findAllOrdersByUser(id);
		ArrayList<OrderDTO1> orderDtoList = new ArrayList<OrderDTO1>();
		for (Order order : orderList) {
			OrderDTO1 orderDto = OrderDTO1.fromEntity(order);
			orderDtoList.add(orderDto);
		}
		System.out.println(orderDtoList);

		HashMap<String, Object> result = new HashMap<String, Object>();
		if (orderDtoList != null) {
			result.put("status", "success");
			result.put("data", orderDtoList);
			return ResponseEntity.ok(result);
		}
		result.put("status", "error");
		return ResponseEntity.ok(result);
	}	
	@PutMapping("/editprofile")
	public ResponseEntity<?> editUserProfile(@RequestBody UserDTO dto){
		System.out.println("inside user edit profile");
		System.out.println(dto);
		User user = userService.getById(dto.getUserId());
//		System.out.println(user);
          user = UserDTO.toEntity(dto);
          user = userService.saveUser(user);
          System.out.println(user);
          HashMap<String, Object> result = new HashMap<String, Object>();
  		result.put("status", "success");
  		result.put("Data", user);
  		return ResponseEntity.ok(result);
	}
	@PostMapping("/addAddress")
	public ResponseEntity<?> addAddress(@RequestBody UserDTO dto){
		System.out.println(dto);
		User user = userService.getById(dto.getUserId());
	      user = UserDTO.toEntityForAddAddress(dto, user);
	      System.out.println(user);
	       user = userService.saveUser(user);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("Data", user);
		return ResponseEntity.ok(result);
	}
	@PutMapping("/modifyAddress")
	public ResponseEntity<?> modifyAddress(@RequestBody UserDTO dto){
		System.out.println(dto);
		User user = userService.getById(dto.getUserId());
	      user = UserDTO.toEntityForAddAddress(dto, user);
	      System.out.println(user);
	       user = userService.saveUser(user);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
//		result.put("Data", user);
		return ResponseEntity.ok(result);
	}	
	@PostMapping("/addUserPayment")
	public ResponseEntity<?> addUserPayment(@RequestBody UserPaymentDTO dto ){
		System.out.println("inside add userpayment");
		System.out.println(dto.getUserId());
		System.out.println(dto);
		User user = userService.getById(dto.getUserId());
		UserPayment payment=UserPaymentDTO.toentities(dto);
		payment.setUser(user);
		 payment = paymentServices.savePayment(payment);
		user.setUserPayment(payment);
//		user=userService.saveUser(user);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
//		result.put("Data", user);
		return ResponseEntity.ok(result);	
		}

	
	@GetMapping("/getUserPayment")
	public ResponseEntity<?> getUsepayment(@RequestBody UserDTO dto){
		User user = userService.getById(dto.getUserId());
		UserPayment payment=user.getUserPayment();
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("Data", payment);
		return ResponseEntity.ok(result);
	}
	

	
@GetMapping("/allusers")
public ResponseEntity<?> getAllUsers(){
	List<User> list = userService.getAllUsers();
	ArrayList<UserDTO> userList  = new ArrayList<UserDTO>();
	for( User user : list) {
		userList.add( UserDTO.fromEntity(user)) ;
	}
	return Response.success(userList);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteUser(@PathVariable int id){
	userService.deleteUser(id);
	return Response.success("success");
	
}
}













