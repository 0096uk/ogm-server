package com.greenmart.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="requirement_list")
public class RequirementList extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="req_id")
	private int reqId;
	@Column(nullable = false)
	private int quantity;
	
	@OneToOne(mappedBy = "requirementList")
	private Product product;

	public RequirementList() {
	}

	public RequirementList(int reqId, int quantity, Product product) {
		this.reqId = reqId;
		this.quantity = quantity;
		this.product = product;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "RequirementList [reqId=" + reqId + ", quantity=" + quantity + ", product=" + product + "]";
	}

}
