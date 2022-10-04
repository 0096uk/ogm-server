package com.greenmart.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenmart.pojos.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Integer> {
	Supplier findByEmail(String email);

	List<Supplier> findAll();

}
