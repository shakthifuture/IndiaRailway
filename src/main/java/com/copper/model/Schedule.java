package com.copper.model;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="train_id")
	private Train train;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="source_station_id")
	private Station sourceStation;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="destination_station_id")
	private Station destinationStation;
	
	@Column(name="arrival_time")
	private Time arrivalTime;
	
	@Column(name="depature_time")
	private Time departureTime;
	
	@Column(name="sunday")
	private boolean sunday;
	
	@Column(name="monday")
	private boolean monday;
	
	@Column(name="tuesday")
	private boolean tuesday;
	
	@Column(name="wednesday")
	private boolean wednesday;
	
	@Column(name="thursday")
	private boolean thursday;
	
	@Column(name="friday")
	private boolean friday;
	
	@Column(name="saturday")
	private boolean saturday;
	
	@Column(name="is_suspended")
	private boolean isSuspended;
}
