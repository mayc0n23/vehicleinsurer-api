package br.com.vehicle.insurer.api.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PolicyDTO {
	
	private String id;
	
	private int policyNumber;
	
	@NotNull(message = "O inicio de vigência não pode ficar vazio")
	private LocalDateTime startOfTerm;
	
	@NotNull(message = "O fim de vigência não pode ficar vazio")
	private LocalDateTime endOfTerm;
	
	@NotBlank(message = "A placa do veículo não pode ficar vazia")
	private String vehicleLicensePlate;
	
	@PositiveOrZero(message = "O valor da apólice não pode ser menor que zero")
	private double policyValue;
	
}