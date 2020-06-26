package com.copper.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "train")
@Getter
@Setter
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="train_number")
	private int trainNumber;
	
	@Column(name="train_name")
	private String trainName;
	
	@OneToMany	
	private Set<Schedule> schedule;
}
