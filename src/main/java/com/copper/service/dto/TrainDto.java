package com.copper.service.dto;

import com.copper.model.Train;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainDto {
	private Integer id;
	private Integer trainNumber;
	private String trainName;
	
	public TrainDto() {
		
	}
	
	public TrainDto(Train train) {
		this.id = train.getId();
		this.trainNumber = train.getTrainNumber();
		this.trainName = train.getTrainName();
	}
}
