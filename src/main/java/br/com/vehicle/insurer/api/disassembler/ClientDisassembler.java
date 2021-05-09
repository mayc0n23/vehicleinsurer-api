package br.com.vehicle.insurer.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vehicle.insurer.api.dto.ClientDTO;
import br.com.vehicle.insurer.domain.model.Client;

@Component
public class ClientDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Client toDomainObject(ClientDTO clientDTO) {
		return modelMapper.map(clientDTO, Client.class);
	}
	
	public void copyToDomainObject(ClientDTO clientDTO, Client client) {
		modelMapper.map(clientDTO, client);
	}
	
}