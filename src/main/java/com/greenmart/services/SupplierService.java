package com.greenmart.services;

import java.util.List;

import com.greenmart.pojos.Employee;
import com.greenmart.pojos.Supplier;
import com.greenmart.pojos.User;

public interface SupplierService {
	Supplier getByEmail(String email);

	Supplier authenticate(String email, String password);

	List<Supplier> findAll();

	Supplier saveSupplier(Supplier supplier);

	Supplier updatePassword(String email, String currentPassword , String newPassword);

	boolean deleteSupplier(int id);

	void deleteThisSupplier(int id);

}
