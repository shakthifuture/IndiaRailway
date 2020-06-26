package com.copper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.copper.model.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer>{
	public Train findByTrainNumber(int trainNumber); 
}
