package com.greenmart.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greenmart.daos.SupplierDao;
import com.greenmart.pojos.Supplier;
import com.greenmart.pojos.User;

@Transactional
@Service
public class SupplierServiceImpl implements SupplierService {
@Autowired
private SupplierDao supplierDao;

@Autowired
private PasswordEncoder bcryEncoder;

	@Override
	public Supplier getByEmail(String email) {
		Supplier supplier = supplierDao.findByEmail(email);
		return supplier;
	}
	@Override
	public Supplier authenticate(String email, String password) {
		Supplier supplier = getByEmail(email);
		if(bcryEncoder.matches(password, supplier.getPassword()))
		return supplier;
		return null;
	}
	@Override
	public List<Supplier> findAll() {
		// TODO Auto-generated method stub
		List<Supplier> list = supplierDao.findAll();
		return list;
	}
	@Override
	public Supplier saveSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		supplier.setCreatedAt(new Date());
		supplier.setModifiedAt(new Date());
		Supplier supplier2 = supplierDao.save(supplier);
		return supplier2;
	}

	@Override
	public boolean deleteSupplier(int id) {
		// TODO Auto-generated method stub
		if(supplierDao.existsById(id))
		{
			supplierDao.deleteById(id);
			return true;
		}
		return false;
	}
	@Override
	public Supplier updatePassword(String email, String currentPassword, String newPassword) {
		System.out.println(email + currentPassword + newPassword);
		Supplier supplier = supplierDao.findByEmail(email);
		if( bcryEncoder.matches(currentPassword, supplier.getPassword()))
		{
			System.out.println("inside supplier password matrched");
			String newpass = bcryEncoder.encode(newPassword);
			supplier.setPassword(newpass);
		}
		else	
			return null;
		supplier = supplierDao.save(supplier);
		return supplier;
	}
	@Override
	public void deleteThisSupplier(int id) {
		supplierDao.deleteById(id);
		
		
	}

}
