package com.greenmart.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenmart.pojos.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
Employee findByEmail(String email);
	List<Employee> findAll();
	
//	@Query(value = "SELECT * from employee e  ORDER BY RAND() LIMIT 1" , nativeQuery = true )
	
@Query(value = "SELECT * from employee e  where e.role='employee'  ORDER BY RAND() LIMIT 1" , nativeQuery = true )
	Employee getRandomEmployee();
}
