package br.com.vehicle.insurer.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Policy {
	
	@Id
	private String id;
	
	private int policyNumber;
	
	private LocalDateTime startOfTerm;
	
	private LocalDateTime endOfTerm;
	
	private String vehicleLicensePlate;
	
	private double policyValue;
	
}