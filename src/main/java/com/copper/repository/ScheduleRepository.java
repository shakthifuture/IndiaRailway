package com.copper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.copper.model.Schedule;
import com.copper.model.Station;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	public List<Schedule> findBySourceStation(Station station);
	
}
