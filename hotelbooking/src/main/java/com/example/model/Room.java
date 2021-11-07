package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "room")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Room implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * id denotes room identification
	 */
	@Id
	@Column(name="id")
    private String id;

	/*
	 * type denotes classification of room
	 */
	@Column(name="type")
	private String type;
	
	/*
	 * rent denotes room rent amount
	 */
	@Column(name="rent")
	private Double rent;
	
	/*
	 * numberofbeds denotes total number of beds available in the room
	 */
	@Column(name="numberofbeds")
	private int numberofbeds;
	
	/*
	 * vacant denotes room is vacant or not flag
	 */
	@Column(name="vacant")
	private boolean vacant;

}
