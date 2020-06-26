package com.copper.service.mapper;

import org.springframework.stereotype.Service;

import com.copper.model.Station;
import com.copper.service.dto.StationDto;

@Service
public class StationMapper implements EntityMapper<StationDto, Station>{

	@Override
	public StationDto toDto(Station entity) {
		return new StationDto(entity);
	}
	
	@Override
	public Station toEntity(StationDto dto) {
		if(dto == null) {
			return null;
		} else {
			Station station = new Station();
			station.setId(dto.getId());
			station.setStationCode(dto.getStationCode());
			station.setStationName(dto.getStationName());
			station.setLongitude(dto.getLongitude());
			station.setLatitude(dto.getLatitude());
			return station;
		}
	}

}
