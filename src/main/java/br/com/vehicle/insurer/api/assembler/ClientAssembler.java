package br.com.vehicle.insurer.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vehicle.insurer.api.dto.ClientDTO;
import br.com.vehicle.insurer.domain.model.Client;

@Component
public class ClientAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClientDTO toDTO(Client client) {
		return modelMapper.map(client, ClientDTO.class);
	}
	
	public List<ClientDTO> toCollectionDTO(List<Client> clients) {
		return clients.stream()
				.map(client -> toDTO(client))
				.collect(Collectors.toList());
	}
	
}