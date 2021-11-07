package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.BookingHistory;

public interface BookingHistoryRepository  extends JpaRepository<BookingHistory, Long> {

}
