package com.copper.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.copper.model.Station;
import com.copper.repository.StationRepository;
import com.copper.service.dto.StationDto;
import com.copper.service.mapper.StationMapper;

@Service
@Transactional
public class StationService {

	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private StationMapper stationMapper;
	
	@Transactional(readOnly = true)
	public List<StationDto> getAllTrains(){
		return stationRepository.findAll().stream()
				.map(stationMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public StationDto saveStation(StationDto stationDto) {
		Station station = stationMapper.toEntity(stationDto);
		station = stationRepository.save(station);
		return stationMapper.toDto(station);
	}
	
	@Transactional(readOnly = true)
	public List<StationDto> searchStation(String query){
		return stationRepository.findByStationNameContainingIgnoreCase(query).stream()
				.map(stationMapper::toDto)
				.collect(Collectors.toList());
	}
}
