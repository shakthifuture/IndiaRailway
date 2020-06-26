package com.copper.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copper.service.TrainService;
import com.copper.service.dto.TrainDto;
import com.copper.web.errors.BadRequestAlertException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/train")
public class TrainResource {
	
	
	@Autowired
	private TrainService trainService;
	
	@PostMapping("/save")
	public ResponseEntity<TrainDto> saveTrainDetails(@RequestBody TrainDto trainDto) {
		log.debug("REST request to save Train details: {}", trainDto);
		if(trainDto.getId() != null) {
			throw new BadRequestAlertException("A new train cannot already have an ID");
		}
		return ResponseEntity.ok(trainService.saveTrain(trainDto));
	}
	
	/**
     * {@code GET  /allTrains} : get all the trains.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trains in body.
     */
	@GetMapping("/trains")
	public ResponseEntity<List<TrainDto>> getTrainDetails(){
		log.debug("REST request to get all Trains");
		return ResponseEntity.ok(trainService.getAllTrainDetails());
	}
	
}
