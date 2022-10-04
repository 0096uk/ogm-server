package com.greenmart.pojos;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "orders" )
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orderId")
public class Order extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	@Column(nullable = false, precision = 10, scale = 2)
	private double total;
	@Column(name = "up_id", nullable = false)
	private int upId;
	@Column(nullable = false)
	private int status;
	
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SS a")
	@Column(name = "delivery_date", nullable = false)
	private Date deliveryDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "user_id" )
	private User user;
//   this one changed bcoz of recursion in addorder
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItemList;

	public Order() {

	}

	public Order(int orderId, double total, int upId, int status, Date createdAt, Date modifiedAt, Date deliveryDate,
			Employee employee, User user, List<OrderItem> orderItemList) {
		super();
		this.orderId = orderId;
		this.total = total;
		this.upId = upId;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.deliveryDate = deliveryDate;
		this.employee = employee;
		this.user = user;
		this.orderItemList = orderItemList;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getUpId() {
		return upId;
	}

	public void setUpId(int upId) {
		this.upId = upId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

//	@Override
//	public String toString() {
//		return "Order [orderId=" + orderId + ", total=" + total + ", upId=" + upId + ", status=" + status
//				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", deliveryDate=" + deliveryDate
//				+ ", employee=" + employee + ", user=" + user + ", orderItemList=" + orderItemList + "]";
//	}

}
