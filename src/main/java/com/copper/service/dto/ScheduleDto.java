package com.copper.service.dto;

import com.copper.model.Schedule;
import com.copper.service.mapper.StationMapper;
import com.copper.service.mapper.TrainMapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private StationMapper stationMapper;
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private TrainMapper trainMapper;
	
	private Integer id;
	private TrainDto train;
	private StationDto sourceStation;
	private StationDto destinationStation;
	private String arrivalTime;
	private String departureTime;
	private boolean sunday;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
	
	public ScheduleDto(){
		
	}
	
	public ScheduleDto(Schedule schedule) {
		this.trainMapper = new TrainMapper();
		this.stationMapper = new StationMapper();
		this.id = schedule.getId();
		this.train = trainMapper.toDto(schedule.getTrain());
		this.sourceStation = stationMapper.toDto(schedule.getSourceStation());
		this.destinationStation = stationMapper.toDto(schedule.getDestinationStation());
		this.arrivalTime = schedule.getArrivalTime().toString();
		this.departureTime = schedule.getDepartureTime().toString();
		this.sunday = schedule.isSunday();
		this.monday = schedule.isMonday();
		this.tuesday = schedule.isTuesday();
		this.wednesday = schedule.isWednesday();
		this.thursday = schedule.isThursday();
		this.friday = schedule.isFriday();
		this.saturday = schedule.isSaturday();
	}
}
