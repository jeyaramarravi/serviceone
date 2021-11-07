package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.BookingHistory;
import com.example.model.BookingHistoryResponse;
import com.example.model.CommonRequest;
import com.example.model.CommonResponse;
import com.example.model.Room;
import com.example.repository.BookingHistoryRepository;
import com.example.repository.RoomRepository;

@RestController
@RequestMapping("/api")
public class ApplicationController {
	
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	BookingHistoryRepository bookingHistoryRepository;
	
	@RequestMapping(value = "/show_vacant_rooms", method = RequestMethod.GET)
	public List<Room> showVacantRooms() {
		return roomRepository.findByVacant(true);
	}
	
	@RequestMapping(value = "/show_occupied_rooms", method = RequestMethod.GET)
	public List<Room> showOccupiedRooms() {
		return roomRepository.findByVacant(false);
	}
	
	@RequestMapping(value = "/show_all_rooms", method = RequestMethod.GET)
	public List<Room> showAllRooms() {
		return roomRepository.findAll();
	}
	
	@PostMapping("/room")
	public ResponseEntity<?> createRoom(@RequestBody Room room) {
		Room obj = roomRepository.save(room);
		HttpStatus status = HttpStatus.CREATED;
		if(obj.getId().isEmpty()) {
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(obj);
	}
	
	@PostMapping("/hotelbooking")
	public ResponseEntity<?> newBooking(@RequestBody BookingHistory booking) {
		BookingHistoryResponse toRet = new BookingHistoryResponse();
		HttpStatus status = HttpStatus.CREATED;
		Optional<Room> result = roomRepository.findById(booking.getRoomNumber());
		if(result.isPresent()) {
			Room room = result.get();
			if(room.isVacant()) {
				BookingHistory obj = bookingHistoryRepository.save(booking);
				toRet.setBookingId(obj.getId());
				toRet.setResponseMessage("Room booked successfully");
				updateRoomStatus(obj.getRoomNumber(), false);
			} else {
				status = HttpStatus.IM_USED;
				toRet.setResponseMessage("Room is already occupied");
			}
		} else {
			status = HttpStatus.NOT_FOUND;
			toRet.setResponseMessage("Invalid room number");
		}
		toRet.setCode(status);
		return ResponseEntity.status(status).body(toRet);
	}
	
	@PutMapping("/check_in/{id}")
	public ResponseEntity<?> checkIn(@RequestBody CommonRequest request, @PathVariable Long id) {
		CommonResponse toRet = new CommonResponse();
		String time = request.getTime();
		Date actualDate = getFormattedDate(time);
		HttpStatus status = HttpStatus.OK;
		if(actualDate != null) {
			Optional<BookingHistory> result = bookingHistoryRepository.findById(id);
			if(result.isPresent()) {
				BookingHistory bookingHistory = result.get();
				bookingHistory.setCheckInTime(actualDate);
				bookingHistoryRepository.save(bookingHistory);
				toRet.setResponseMessage("check in success");
			} else {
				status = HttpStatus.NOT_FOUND;
				toRet.setResponseMessage("check in failure, please provide valid booking id");
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
			toRet.setResponseMessage("invalid time");
		}
		toRet.setCode(status);
		return ResponseEntity.status(status).body(toRet);
	}
	
	@PutMapping("/check_out/{id}")
	public ResponseEntity<?> checkOut(@RequestBody CommonRequest request, @PathVariable Long id) {
		CommonResponse toRet = new CommonResponse();
		String time = request.getTime();
		Date actualDate = getFormattedDate(time);
		HttpStatus status = HttpStatus.OK;
		if(actualDate != null) {
			Optional<BookingHistory> result = bookingHistoryRepository.findById(id);
			if(result.isPresent()) {
				BookingHistory bookingHistory = result.get();
				bookingHistory.setCheckOutTime(actualDate);
				bookingHistoryRepository.save(bookingHistory);
				toRet.setResponseMessage("check out success");
				updateRoomStatus(bookingHistory.getRoomNumber(), true);
			} else {
				status = HttpStatus.NOT_FOUND;
				toRet.setResponseMessage("check in failure, please provide valid booking id");
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
			toRet.setResponseMessage("invalid time");
		}
		toRet.setCode(status);
		return ResponseEntity.status(status).body(toRet);
	}
	
	private void updateRoomStatus(String roomId, boolean vacantFlag) {
		Optional<Room> result = roomRepository.findById(roomId);
		if(result.isPresent()) {
			Room room = result.get();
			room.setVacant(vacantFlag);
			roomRepository.save(room);
		}
	}
	
	private boolean isVacant(String roomId) {
		boolean flag = false;
		Optional<Room> result = roomRepository.findById(roomId);
		if(result.isPresent()) {
			Room room = result.get();
			flag = room.isVacant();
		}
		return flag;
	}
	
	private Date getFormattedDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		Date d = null;
		try {
			sdf.setLenient(false);
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			d = null;
		}
		return d;
	}
	
}
