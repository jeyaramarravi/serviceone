package com.example.model;

import lombok.Data;

@Data
public class RoomDAO {
	public RoomDAO(Room room) {
		super();
		this.setId(room.getId());
		this.setType(room.getType());
		this.setNumberofbeds(room.getNumberofbeds());
		this.setRent(room.getRent());
		this.setVacant(room.isVacant());
	}
	/*
	 * id denotes room identification
	 */
    private String id;


	/*
	 * type denotes classification of room
	 */
	private String type;
	
	/*
	 * rent denotes room rent amount
	 */
	private Double rent;
	
	/*
	 * numberofbeds denotes total number of beds available in the room
	 */
	private int numberofbeds;
	
	/*
	 * vacant denotes room is vacant or not flag
	 */
	private boolean vacant;
}
