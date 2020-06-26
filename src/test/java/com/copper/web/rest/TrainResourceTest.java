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

import com.copper.model.Train;
import com.copper.repository.TrainRepository;
import com.copper.service.dto.TrainDto;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainResourceTest {
	
	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private MockMvc mockMvc;
	
	private static final int TRAIN_NUMBER = 76555;
	private static final String TRAIN_NAME = "Delhi Exp";
	
	@Test
	@WithMockUser
    @Transactional
    public void getTrainList() throws Exception {
		Train train = createTrainEntity();
		trainRepository.save(train);
        
        mockMvc.perform(get("/api/train/trains")
        		.contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].trainName").value(TRAIN_NAME));
	}
	
	@Test
	@WithMockUser
    @Transactional
    public void saveTrain() throws Exception {
		TrainDto trainDto = new TrainDto();
		trainDto.setTrainName(TRAIN_NAME);
		trainDto.setTrainNumber(TRAIN_NUMBER);
        
        mockMvc.perform(post("/api/station/save")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(TestUtil.convertObjectToJsonBytes(trainDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").isNotEmpty());
	}
	
	private Train createTrainEntity() {
		Train train = new Train();
		train.setTrainName(TRAIN_NAME);
		train.setTrainNumber(TRAIN_NUMBER);
        return train;
	}
}
