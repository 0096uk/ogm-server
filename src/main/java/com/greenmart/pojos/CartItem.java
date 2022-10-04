package com.greenmart.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_item")
public class CartItem extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="ci_id")
	private int ciId;
	@Column(nullable = false)
	private int quantity;

	
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public CartItem() {
	}



	public CartItem(int ciId, int quantity, Cart cart, Product product) {
		this.ciId = ciId;
		this.quantity = quantity;
		this.cart = cart;
		this.product = product;
	}



	public int getCiId() {
		return ciId;
	}

	public void setCiId(int ciId) {
		this.ciId = ciId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}



	@Override
	public String toString() {
		return "CartItem [ciId=" + ciId + ", quantity=" + quantity + ", cart=" + cart + ", product=" + product + "]";
	}



}
