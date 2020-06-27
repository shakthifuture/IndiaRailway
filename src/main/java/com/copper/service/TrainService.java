package com.copper.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.copper.service.mapper.TrainMapper;
import com.copper.model.Train;
import com.copper.repository.TrainRepository;
import com.copper.service.dto.TrainDto;

@Service
@Transactional
public class TrainService {

	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private TrainMapper trainMapper;
	
	public TrainDto saveTrain(TrainDto trainDto) {
		Train train = trainMapper.toEntity(trainDto);
		train = trainRepository.save(train);
		return trainMapper.toDto(train);
	}
	
	@Transactional(readOnly = true)
	public List<TrainDto> getAllTrainDetails(){
		return trainRepository.findAll().stream()
				.map(trainMapper::toDto)
				.sorted(Comparator.comparing(TrainDto::getTrainName, String.CASE_INSENSITIVE_ORDER))
				.collect(Collectors.toList());
	}
	
}
