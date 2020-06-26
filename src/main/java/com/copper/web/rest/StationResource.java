package com.copper.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.copper.service.StationService;
import com.copper.service.dto.StationDto;
import com.copper.web.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api/station")
public class StationResource {
	
	@Autowired
	private StationService stationService;

	@GetMapping("/stations")
	public ResponseEntity<List<StationDto>> getAllStations(){
		return ResponseEntity.ok(stationService.getAllTrains());
	}
	
	@PostMapping("/save")
	public ResponseEntity<StationDto> saveStation(@RequestBody StationDto stationDto) {
		if(stationDto.getId() != null) {
			throw new BadRequestAlertException("A new station cannot already have an ID");
		}
		return ResponseEntity.ok(stationService.saveStation(stationDto));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<StationDto>> searchStation(@RequestParam String query){
		return ResponseEntity.ok(stationService.searchStation(query));
	}
}
