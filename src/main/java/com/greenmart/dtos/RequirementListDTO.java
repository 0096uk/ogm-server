package com.greenmart.dtos;

import org.springframework.beans.BeanUtils;

import com.greenmart.pojos.RequirementList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequirementListDTO {
	private int reqId;
	private int quantity;
	private String productName;

	public static RequirementListDTO fromEntity(RequirementList entity) {
		RequirementListDTO dto = new RequirementListDTO();
		BeanUtils.copyProperties(entity, dto);
//		dto.setProductName(entity.getProduct().getName());
		return dto;
	}
}
