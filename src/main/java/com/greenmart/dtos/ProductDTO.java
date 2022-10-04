package com.greenmart.dtos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenmart.pojos.Product;
import com.greenmart.pojos.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductDTO {

	private int productId;
	private String name;
	private String description;
	private double price;

	private MultipartFile thumbnail;
	private int status;

	private String thumbnailString;

	private int quantity;

	private String category;

	private int initialQuantity;

	private int presentQuantity;
	
	private int supplierId ;

	@JsonIgnore
	private SupplierDTO supplierDto;

	public static Product toEntity(ProductDTO dto) {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product, "thumbnail");
		return product;
	}

	public String getThumbnailString() {
		return thumbnailString;
	}

	public void setThumbnailString(String thumbnailString) {
		this.thumbnailString = thumbnailString;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getInitialQuantity() {
		return initialQuantity;
	}

	public void setInitialQuantity(int initialQuantity) {
		this.initialQuantity = initialQuantity;
	}

	public int getPresentQuantity() {
		return presentQuantity;
	}

	public void setPresentQuantity(int presentQuantity) {
		this.presentQuantity = presentQuantity;
	}

	public SupplierDTO getSupplierDto() {
		return supplierDto;
	}

	public void setSupplierDto(SupplierDTO supplierDto) {
		this.supplierDto = supplierDto;
	}
	
	

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", name=" + name + ", description=" + description + ", price="
				+ price + ", thumbnail=" + thumbnail + ", status=" + status + ", thumbnailString=" + thumbnailString
				+ ", quantity=" + quantity + ", category=" + category + ", initialQuantity=" + initialQuantity
				+ ", presentQuantity=" + presentQuantity + ", supplierId=" + supplierId + ", supplierDto=" + supplierDto
				+ "]";
	}

	public static ProductDTO fromEntity(Product product) {
		ProductDTO productDto = new ProductDTO();
		BeanUtils.copyProperties(product, productDto);
		productDto.setThumbnailString(product.getThumbnail());
		productDto.setCategory(product.getCategory());
		productDto.setSupplierDto(SupplierDTO.fromEntity(product.getSupplier()));

		return productDto;
	}
	
	

}
