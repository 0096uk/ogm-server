package com.greenmart.pojos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
import com.greenmart.pojos.User;
@Entity
@Table(name="cart")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "cartId")
public class Cart extends BaseEntity{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="cart_id",nullable = false)
private int cartId;
@Column(nullable = false, precision = 10, scale = 2 )
private double total;
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

@OneToOne
@JoinColumn(name= "user_id")
private User user;

//@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
@OneToMany(mappedBy = "cart")
private List<CartItem> cItem;
  public Cart() {
	
}

public Cart(int cartId, double total, Date createdAt, Date modifiedAt, User user, List<CartItem> cItem) {
	super();
	this.cartId = cartId;
	this.total = total;
	this.createdAt = createdAt;
	this.modifiedAt = modifiedAt;
	this.user = user;
	this.cItem = cItem;
}

public int getCartId() {
	return cartId;
}

public void setCartId(int cartId) {
	this.cartId = cartId;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
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

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public List<CartItem> getcItem() {
	return cItem;
}

public void setcItem(List<CartItem> cItem) {
	this.cItem = cItem;
}

@Override
public String toString() {
	return "Cart [cartId=" + cartId + ", total=" + total + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
			+ "]";
}


//@Override
//public String toString() {
//	return "Cart [cartId=" + cartId + ", total=" + total + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
//			+ "]";
//}

//@Override
//public String toString() {
//	return "Cart [cartId=" + cartId + ", total=" + total + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
//			+ ", user=" + user + ", cItem=" + cItem + "]";
//}
//

  
}
