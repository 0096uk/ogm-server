package com.greenmart.pojos;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
public class Product extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	@Column(length = 50, nullable = false)
	private String name;
	@Column(length = 300)
	private String description;
	@Column(nullable = false)
	private double price;

	@Nullable
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "created_at")
	private Date createdAt;
	
	@Nullable
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	
	@Nullable
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "deleted_at")
	private Date deletedAt;

	@Column(length = 300, nullable = false)
	private String thumbnail;
	@Column(nullable = false, columnDefinition = "int default 0")
	private int status;
	@Column(length = 20, nullable = false)
	private String category;
	
	@Column( name="initial_quantity" , nullable = false )
	private int initialQuantity;
	
	@Column( name = "present_quantity" , nullable = false)
	private int presentQuantity;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier ;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private RequirementList requirementList;

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public RequirementList getRequirementList() {
		return requirementList;
	}

	public void setRequirementList(RequirementList requirementList) {
		this.requirementList = requirementList;
	}

	public Product(int productId, String name, String description, double price, Date createdAt, Date modifiedAt,
			Date deletedAt, String thumbnail, int status, String category, int initialQuantity, int presentQuantity,
			Supplier supplier, RequirementList requirementList) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.deletedAt = deletedAt;
		this.thumbnail = thumbnail;
		this.status = status;
		this.category = category;
		this.initialQuantity = initialQuantity;
		this.presentQuantity = presentQuantity;
		this.supplier = supplier;
		this.requirementList = requirementList;
	}

	public Product() {
	}

	
	
	 


}
