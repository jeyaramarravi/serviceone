package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Room;

@SpringBootTest
public class HotelBookingTest {

	@Test
	public void testRoom() {
		Room r = new Room();
		r.setId("T101");
		r.setNumberofbeds(2);
		r.setRent(1000.0);
		r.setType("Delux");
		r.setVacant(true);
		assertNotNull("Test Room type is not null", r.getType());
		assertEquals("Room id equal to T101", r.getId(), "T101");
	}
	
}
