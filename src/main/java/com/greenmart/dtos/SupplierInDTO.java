package com.greenmart.dtos;

import org.springframework.beans.BeanUtils;
import com.greenmart.pojos.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierInDTO {
	private int supplierId;
	private String email;
	private String password;
	private String phone;
	private String firstName;
	private String lastName;

	
	public static Supplier toEntity(SupplierInDTO dto) {
		Supplier entity = new Supplier();
		BeanUtils.copyProperties(dto,entity);
		return entity;
	}

}
