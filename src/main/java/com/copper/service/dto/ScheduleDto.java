package com.copper.service.dto;

import java.sql.Time;

import com.copper.model.Schedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
	private Integer id;
	private int trainNumber;
	private String sourceStationCode;
	private String desginationStationCode;
	private Time arrivalTime;
	private Time departureTime;
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
		this.id = schedule.getId();
		this.trainNumber = schedule.getTrain().getTrainNumber();
		this.sourceStationCode = schedule.getSourceStation().getStationCode();
		this.desginationStationCode = schedule.getDesginationStation().getStationCode();
		this.arrivalTime = schedule.getArrivalTime();
		this.departureTime = schedule.getDepartureTime();
		this.sunday = schedule.isSunday();
		this.monday = schedule.isMonday();
		this.tuesday = schedule.isTuesday();
		this.wednesday = schedule.isWednesday();
		this.thursday = schedule.isThursday();
		this.friday = schedule.isFriday();
		this.saturday = schedule.isSaturday();
	}
}
