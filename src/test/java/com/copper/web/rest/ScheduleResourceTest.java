package com.copper.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Time;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.copper.model.Schedule;
import com.copper.model.Station;
import com.copper.model.Train;
import com.copper.repository.ScheduleRepository;
import com.copper.repository.StationRepository;
import com.copper.repository.TrainRepository;
import com.copper.service.dto.ScheduleDto;
import com.copper.service.dto.StationDto;
import com.copper.service.dto.TrainDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleResourceTest {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TrainRepository trainRepository;
	
	private static final String STATION_CODE = "007";
	private static final String STATION_NAME = "Tambaram";
	private static final String LOGITUDE = "85.000005";
	private static final String LATITUDE = "78.45545";
	
	private static final int TRAIN_NUMBER = 76555;
	private static final String TRAIN_NAME = "Delhi Exp";
	
	@Test
	@WithMockUser
    @Transactional
    public void searchSchedule() throws Exception {
		Station station = createStationEntity();
        stationRepository.save(station);
        Train train = createTrainEntity();
        trainRepository.save(train);
        
        Schedule schedule = createSchedule(train, station);
        scheduleRepository.save(schedule); 
        
        mockMvc.perform(get("/api/schedule/search/station/"+station.getId())
        		.contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].sourceStation.stationName").value(STATION_NAME));
	}
	
	@Test
	@WithMockUser
    @Transactional
    public void saveSchedule() throws Exception {
		Station station = createStationEntity();
        stationRepository.save(station);
        Train train = createTrainEntity();
        trainRepository.save(train);
        
        StationDto stationDto = createStationDto();
        stationDto.setId(station.getId());
        TrainDto trainDto = createTrainDto();
        trainDto.setId(train.getId());
        
        ScheduleDto scheduleDto = createScheduleDto(trainDto, stationDto);
        
        mockMvc.perform(post("/api/station/save")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(TestUtil.convertObjectToJsonBytes(scheduleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").isNotEmpty());
	}
	
	@Test
	@WithMockUser
    @Transactional
    public void searchStation() throws Exception {
		Station station = createStationEntity();
        stationRepository.save(station);
        Train train = createTrainEntity();
        trainRepository.save(train);
        
        Schedule schedule = createSchedule(train, station);
        scheduleRepository.save(schedule);   
        
        mockMvc.perform(get("/api/schedule/schedules")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(TestUtil.convertObjectToJsonBytes("tam")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].sourceStation.stationName").value(STATION_NAME));
	}
	
	private Station createStationEntity() {
		Station station = new Station();
		station.setStationCode(STATION_CODE);
		station.setStationName(STATION_NAME);
		station.setLongitude(LOGITUDE);
		station.setLatitude(LATITUDE);
        return station;
	}
	
	private StationDto createStationDto() {
		StationDto stationDto = new StationDto();
		stationDto.setStationCode(STATION_CODE);
		stationDto.setStationName(STATION_NAME);
		stationDto.setLongitude(LOGITUDE);
		stationDto.setLatitude(LATITUDE);
        return stationDto;
	}
	
	private Train createTrainEntity() {
		Train train = new Train();
		train.setTrainName(TRAIN_NAME);
		train.setTrainNumber(TRAIN_NUMBER);
        return train;
	}
	
	private TrainDto createTrainDto() {
		TrainDto trainDto = new TrainDto();
		trainDto.setTrainName(TRAIN_NAME);
		trainDto.setTrainNumber(TRAIN_NUMBER);
        return trainDto;
	}
	
	private ScheduleDto createScheduleDto(TrainDto trainDto, StationDto stationDto) {
		ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTrain(trainDto);
        scheduleDto.setSourceStation(stationDto);
        scheduleDto.setDestinationStation(stationDto);
        scheduleDto.setArrivalTime("10:00");
        scheduleDto.setDepartureTime("12:00");
        scheduleDto.setSunday(true);
        scheduleDto.setMonday(true);
        scheduleDto.setTuesday(true);
        scheduleDto.setWednesday(true);
        scheduleDto.setThursday(true);
        scheduleDto.setFriday(true);
        scheduleDto.setSaturday(true);
        return scheduleDto;
	}
	
	private Schedule createSchedule(Train train, Station station) {
		Schedule schedule = new Schedule();
		schedule.setTrain(train);
		schedule.setSourceStation(station);
		schedule.setDestinationStation(station);
		schedule.setArrivalTime(new Time(10,10,10));
		schedule.setDepartureTime(new Time(11,10,10));
		schedule.setSunday(true);
		schedule.setMonday(true);
		schedule.setTuesday(true);
		schedule.setWednesday(true);
        schedule.setThursday(true);
        schedule.setFriday(true);
        schedule.setSaturday(true);
        return schedule;
	}
	
}
