package com.greenmart.dtos;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.greenmart.pojos.Product;

public class ProductImageDTO {

private int productId;
private String name;
private String description;
private double price;
private MultipartFile thumbnail;
private int status;

private String categoryName ;

public ProductImageDTO() {
	// TODO Auto-generated constructor stub
}


public ProductImageDTO(int productId, String name, String description, double price, MultipartFile thumbnail,
		int status, String categoryName) {
	super();
	this.productId = productId;
	this.name = name;
	this.description = description;
	this.price = price;
	this.thumbnail = thumbnail;
	this.status = status;
	this.categoryName = categoryName;
}


public int getProductId() {
	return productId;
}

public void setProductId(int productId) {
	this.productId = productId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public MultipartFile getThumbnail() {
	return thumbnail;
}
public void setThumbnail(MultipartFile thumbnail) {
	this.thumbnail = thumbnail;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public String getcategoryName() {
	return categoryName;
}

public void setcategoryName(String categoryName) {
	this.categoryName = categoryName;
}

@Override
public String toString() {
	return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", price=" + price
			+ ", thumbnail=" + thumbnail + ", status=" + status + ", CategoryName=" + categoryName + "]";
}

public static Product toEntity(ProductImageDTO dto) {
	System.out.println("incoming param "+dto);
	Product product = new Product();
	BeanUtils.copyProperties(dto, product,"thumbnail");
//	product.getProductCategory().setName(dto.categoryName);
//	if ( dto.categoryName.equals("fruits") )
//		product.getProductCategory().setCategoryId(1);
//	else if( dto.categoryName.equals("vegetables"))
//		product.getProductCategory().setCategoryId(2);
//	System.out.println("returning product ="+product);
	return product;
}

}
