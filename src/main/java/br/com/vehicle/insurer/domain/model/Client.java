package br.com.vehicle.insurer.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Client {
	
	@Id
	private String id;
	
	private String fullname;
	
	private String cpf;
	
	private String city;
	
	private String uf;
	
}