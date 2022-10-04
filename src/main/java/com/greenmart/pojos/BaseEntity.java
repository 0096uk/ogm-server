package com.greenmart.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass // To tell Hibernate : whatever follows is the common super class , used for
					// creating enities. Hibernate should not create a separate table for this
@Getter
@Setter
public class BaseEntity {

	@Version // for adding version-based optimistic concurrency control
	private int version;
	
}