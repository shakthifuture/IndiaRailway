package com.copper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.copper.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
	public Station findByStationCode(String stationCode);
	
	public List<Station> findByStationNameContainingIgnoreCase(String query);
}
