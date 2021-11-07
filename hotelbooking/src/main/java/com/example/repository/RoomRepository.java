package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByVacant(boolean published);
	Optional<Room> findById(String id);
}
