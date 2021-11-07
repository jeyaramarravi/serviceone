package com.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "booking_history")
@EntityListeners(AuditingEntityListener.class)
@Data
public class BookingHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * id denotes booking identification
	 */
	@Id
	@Column(name="id")
	@GeneratedValue Long id;
	
	/*
	 * customer name
	 */
	private String name;
	
	/*
	 * customer age
	 */
	private int age;
	
	/*
	 * customer address
	 */
	private String address;
	
	/*
	 * customer aadharnumber
	 */
	@Column(name="aadharnumber")
	@JsonProperty("adhar_no")
	private String aadharNumber;
	
	/*
	 * room identification
	 */
	@Column(name="roomnumber")
	@JsonProperty("room_no")
	private String roomNumber;
	
	/*
	 * paid amount
	 */
	private Double amount;
	
	/*
	 * room check in time
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="checkintime")
	@JsonProperty("checkintime")
	private Date checkInTime;
	
	/*
	 * room check out time
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="checkouttime")
	@JsonProperty("checkouttime")
	private Date checkOutTime;
}
