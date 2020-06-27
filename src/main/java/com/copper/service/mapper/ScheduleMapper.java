package com.copper.service.mapper;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copper.model.Schedule;
import com.copper.repository.StationRepository;
import com.copper.repository.TrainRepository;
import com.copper.service.dto.ScheduleDto;

@Service
public class ScheduleMapper implements EntityMapper<ScheduleDto, Schedule>{
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private StationRepository stationRepository;

	@Override
	public ScheduleDto toDto(Schedule entity) {
		return new ScheduleDto(entity);
	}
	
	@Override
	public Schedule toEntity(ScheduleDto dto) {
		if(dto == null) {
			return null;
		} else {
			Schedule schedule = new Schedule();
			schedule.setId(dto.getId());
			schedule.setTrain(trainRepository.findById(dto.getTrain().getId()).get());
			schedule.setSourceStation(stationRepository.findById(dto.getSourceStation().getId()).get());
			schedule.setDestinationStation(stationRepository.findById(dto.getDestinationStation().getId()).get());
			schedule.setArrivalTime(Time.valueOf(dto.getArrivalTime()+":00")); //Time conversion
			schedule.setDepartureTime(Time.valueOf(dto.getDepartureTime()+":00"));
			schedule.setSunday(dto.isSunday());
			schedule.setMonday(dto.isMonday());
			schedule.setTuesday(dto.isTuesday());
			schedule.setWednesday(dto.isWednesday());
			schedule.setThursday(dto.isThursday());
			schedule.setFriday(dto.isFriday());
			schedule.setSaturday(dto.isSaturday());
			return schedule;
		}
	}

}
