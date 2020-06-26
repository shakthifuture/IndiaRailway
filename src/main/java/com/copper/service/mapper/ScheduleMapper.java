package com.copper.service.mapper;

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
			schedule.setTrain(trainRepository.findByTrainNumber(dto.getTrainNumber()));
			schedule.setSourceStation(stationRepository.findByStationCode(dto.getSourceStationCode()));
			schedule.setDesginationStation(stationRepository.findByStationCode(dto.getDesginationStationCode()));
			schedule.setArrivalTime(dto.getArrivalTime());
			schedule.setDepartureTime(dto.getDepartureTime());
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
