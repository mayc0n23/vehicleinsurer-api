package br.com.vehicle.insurer.api.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDTO {
	
	private String id;
	
	@NotBlank(message = "O nome não pode ficar vazio")
	private String fullname;
	
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@NotBlank(message = "A cidade não pode ficar vazia")
	private String city;
	
	@NotBlank(message = "O UF não pode ficar vazio")
	private String uf;
	
}