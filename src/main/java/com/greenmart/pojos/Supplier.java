package com.greenmart.pojos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private int supplierId;
	@Column(length = 30, unique = true, nullable = false)
	private String email;
	@Column(length = 100, nullable = false)
	private String password;
	@Column(length = 15 , unique = true, nullable = false)
	private String phone;
	@Column(name = "first_name", length = 10, nullable = false)
	private String firstName;
	@Column(name = "last_name", length = 10, nullable = false)
	private String lastName;


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

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL )
	private List<Product> productList;
	
	@Transient
	@Nullable
	private String jwtToken;


	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public Supplier() {
	}

	public Supplier(int supplierId, String email, String password, String phone, String firstName, String lastName,
			Date createdAt, Date modifiedAt) {
		this.supplierId = supplierId;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;

		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;

	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", createdAt=" + createdAt + ", modifiedAt="
				+ modifiedAt + "]";
	}

	

}
