package com.copper.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.copper.model.Station;
import com.copper.repository.StationRepository;
import com.copper.service.dto.StationDto;

@SpringBootTest
@AutoConfigureMockMvc
public class StationResourceTest {
	
	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final String STATION_CODE = "007";
	private static final String STATION_NAME = "Tambaram";
	private static final String LOGITUDE = "85.000005";
	private static final String LATITUDE = "78.45545";
	
	@Test
	@WithMockUser
    @Transactional
    public void getStationList() throws Exception {
		Station station = createStationEntity();
        stationRepository.save(station);
        
        mockMvc.perform(get("/api/station/stations")
        		.contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].stationCode").value("007"));
	}
	
	@Test
	@WithMockUser
    @Transactional
    public void saveStation() throws Exception {
		StationDto stationDto = new StationDto();
		stationDto.setStationCode(STATION_CODE);
		stationDto.setStationName(STATION_NAME);
		stationDto.setLongitude(LOGITUDE);
		stationDto.setLatitude(LATITUDE);
        
        mockMvc.perform(post("/api/station/save")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(TestUtil.convertObjectToJsonBytes(stationDto)))
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
        
        mockMvc.perform(get("/api/station/stations")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(TestUtil.convertObjectToJsonBytes("tam")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].stationName").value(STATION_NAME));
	}
	
	private Station createStationEntity() {
		Station station = new Station();
		station.setStationCode(STATION_CODE);
		station.setStationName(STATION_NAME);
		station.setLongitude(LOGITUDE);
		station.setLatitude(LATITUDE);
        return station;
	}
}
