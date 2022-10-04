package com.greenmart.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.greenmart.dtos.ChangePassword;
import com.greenmart.dtos.EmployeeDTO;
import com.greenmart.dtos.OrderDTO1;
import com.greenmart.dtos.Response;
import com.greenmart.dtos.UserDTO;
import com.greenmart.pojos.Employee;
import com.greenmart.pojos.Order;
import com.greenmart.pojos.Supplier;
import com.greenmart.pojos.User;
import com.greenmart.services.EmployeeService;
import com.greenmart.services.OrderService;
import com.greenmart.services.OrderServiceImpl;
import com.greenmart.services.ProductService;
import com.greenmart.services.ProductServiceImpl;

@CrossOrigin
@RequestMapping("employee")
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@PostMapping("/authenticate")
	public ResponseEntity<?> empAuthenticate(@RequestBody EmployeeDTO emp) {
		Employee employee = empService.authenticate(emp.getEmail(), emp.getPassword());
		//System.out.println(employee );
		//EmployeeDTO responseEmp = EmployeeDTO.fromEntity(employee);
		EmployeeDTO responseEmp = EmployeeDTO.fromEntity(employee);
		//System.out.println( responseEmp);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (responseEmp != null) {
			result.put("status", "success");
			result.put("data", responseEmp);
			return ResponseEntity.ok(result);
		}

		result.put("status", "error");
		return ResponseEntity.ok(result);

	}

	@PostMapping("/signup")
	public ResponseEntity<?> employeeSignup(Employee emp){
		if(emp.getRole()!="admin")
		emp.setRole("employee");
		System.out.println("in employee sign up controller");
		System.out.println(emp);
		String newpass = bcryptEncoder.encode(emp.getPassword());
		emp.setPassword(newpass);
		Employee emp1 = empService.saveEmp(emp);
		System.out.println(emp1);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("data", emp1);
		return ResponseEntity.ok(result);
	}

//	@PutMapping("/updatepassword/{id}")
//	public ResponseEntity<?> changePassword(@RequestBody Employee emp,@PathVariable("id") int id){
//		Employee employee = empService.updatePassword(emp, id);
//		employee.setPassword(emp.getPassword());
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		result.put("status", "success");
//		result.put("data", employee);
//		return ResponseEntity.ok(result);
//		
//	}
	@PutMapping("/updatepassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword dto) {
		System.out.println("employee change password controller");
		System.out.println("incoming body " + dto);
		Employee employee = empService.updatePassword(dto.getEmail(), dto.getCurrentPassword(), dto.getNewPassword());
		System.out.println(employee);
		if(employee !=null)
			return Response.success(employee);
		else
			return Response.error(employee);
	}

	@GetMapping("")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = empService.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getEmployeeOrders(@PathVariable("id") int id) {
		//Integer id1 = new Integer(id);
		List<Order> orderList = empService.findAllOrdersByEmployee(id);
		ArrayList<OrderDTO1> orderDtoList = new ArrayList<OrderDTO1>();
		for (Order order : orderList) {
			//OrderDTO1 orderDto = OrderDTO1.fromEntity(order);
			OrderDTO1 orderDto = OrderDTO1.fromEntityforEmployee(order);
			orderDtoList.add(orderDto);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		if (orderDtoList != null) {
			result.put("status", "success");
			result.put("data", orderDtoList);
			return ResponseEntity.ok(result);
		}

		result.put("status", "error");
		return ResponseEntity.ok(result);
	}
//	@PutMapping("/updateStatus/{product_id}/{status}")
//	public int updateStatus(@PathVariable int product_id,@PathVariable int status) {
//		productService.updateStatus(product_id, status);
//		return 1;
//		
//	}
//	@PutMapping("/updateOrderStatus/{order_id}/{status}")
//	public int updateStatus1(@PathVariable int order_id,@PathVariable int status) {
//		orderService.updateStatus(order_id, status);
//		return 1;
//		
//	}
	
	@PutMapping("/updateOrderStatus/{order_id}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable int order_id,@PathVariable int status) {
		System.out.print("1");
		orderService.updateStatus(order_id, status);
		return Response.success("success");
		
	}
	
	@GetMapping("/allemployees")
	public ResponseEntity<?> getAllEmployeesList(){
		List<Employee> list = empService.findAll();
		
		ArrayList<EmployeeDTO> employeeList  = new ArrayList<EmployeeDTO>();
		for( Employee employee : list) {
			employeeList.add( EmployeeDTO.fromEntity(employee)) ;
		}
		return Response.success(employeeList);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id){
		System.out.println("inside delete "+id);
		empService.deleteEmployee(id);
		return Response.success("success");
		
	}
}
