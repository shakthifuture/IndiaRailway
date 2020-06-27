package com.copper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.copper.model.Schedule;
import com.copper.model.Station;
import com.copper.repository.ScheduleRepository;
import com.copper.repository.StationRepository;
import com.copper.service.dto.ScheduleDto;
import com.copper.service.mapper.ScheduleMapper;

@Service
@Transactional
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	@Autowired
	private StationRepository stationRepository;
	
	@Transactional(readOnly = true)
	public List<ScheduleDto> getAllSchedule(){
		return scheduleRepository.findAll().stream()
				.map(scheduleMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public ScheduleDto saveSchedule(ScheduleDto scheduleDto) {
		Schedule schedule = scheduleMapper.toEntity(scheduleDto);
		schedule = scheduleRepository.save(schedule);
		return scheduleMapper.toDto(schedule);
	}
	
	@Transactional(readOnly = true)
	public List<ScheduleDto> getScheduleByStation(Integer id){
		Optional<Station> station = stationRepository.findById(id);
		return scheduleRepository.findBySourceStationOrDestinationStation(station.get(), station.get()).stream()
				.map(scheduleMapper::toDto)
				.collect(Collectors.toList());
	}
	
}
