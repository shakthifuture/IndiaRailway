package com.copper.service.dto;

import com.copper.model.Station;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationDto {
	private Integer id;
	private String stationCode;
	private String stationName;
	private String longitude;
	private String latitude;
	
	public StationDto(){
		
	}
	
	public StationDto(Station station) {
		this.id = station.getId();
		this.stationCode = station.getStationCode();
		this.stationName = station.getStationName();
		this.longitude = station.getLongitude();
		this.latitude = station.getLatitude();
	}
}
