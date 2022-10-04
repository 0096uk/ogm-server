package com.greenmart.pojos;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "order_item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "oiId")
public class OrderItem extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "oi_id")
	private int oiId;
	@Column(nullable = false)
	private int quantity;
	
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public OrderItem() {
	}

	public OrderItem(int oiId, int quantity, Date createdAt, Date modifiedAt, Order order, Product product) {
		this.oiId = oiId;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.order = order;
		this.product = product;
	}

	public int getOiId() {
		return oiId;
	}

	public void setOiId(int oiId) {
		this.oiId = oiId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

	@Override
	public String toString() {
		return "OrderItem [oiId=" + oiId + ", quantity=" + quantity + ", createdAt=" + createdAt + ", modifiedAt="
				+ modifiedAt + ", order=" + order + ", product=" + product + "]";
	}

}
