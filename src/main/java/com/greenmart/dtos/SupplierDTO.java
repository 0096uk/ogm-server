package com.greenmart.dtos;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.greenmart.pojos.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
	private int supplierId;
	private String email;
	private String password;
	private String phone;
	private String firstName;
	private String lastName;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modifiedAt;
	
	public static SupplierDTO fromEntity(Supplier entity) {
		SupplierDTO dto = new SupplierDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}
