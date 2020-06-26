package com.copper.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copper.service.ScheduleService;
import com.copper.service.dto.ScheduleDto;
import com.copper.web.errors.BadRequestAlertException;

@RestController
@RequestMapping("api/schedule")
public class ScheduleResource {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/schedules")
	public ResponseEntity<List<ScheduleDto>> getAllSchedule(){
		return ResponseEntity.ok(scheduleService.getAllSchedule());
	}
	
	@PostMapping("/save")
	public ResponseEntity<ScheduleDto> saveSchedule(@RequestBody ScheduleDto scheduleDto){
		if(scheduleDto.getId() != null) {
			throw new BadRequestAlertException("A new schedule cannot already have an ID");
		}
		return ResponseEntity.ok(scheduleService.saveSchedule(scheduleDto));
	}
	
	@GetMapping("/search/station/{id}")
	public ResponseEntity<List<ScheduleDto>> getSchedules(@PathVariable Integer id) {
		return ResponseEntity.ok(scheduleService.getScheduleByStation(id));
	}
	

}
