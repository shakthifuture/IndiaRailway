package com.copper.service.mapper;

import org.springframework.stereotype.Service;

import com.copper.model.Train;
import com.copper.service.dto.TrainDto;

@Service
public class TrainMapper implements EntityMapper<TrainDto, Train> {
	
	@Override
	public TrainDto toDto(Train train) {
		return new TrainDto(train);
	}

	@Override
	public Train toEntity(TrainDto dto) {
		if(dto == null)
			return null;
		else {
			Train train = new Train();
			train.setId(dto.getId());
			train.setTrainNumber(dto.getTrainNumber());
			train.setTrainName(dto.getTrainName());
			return train;
		}
	}

}
