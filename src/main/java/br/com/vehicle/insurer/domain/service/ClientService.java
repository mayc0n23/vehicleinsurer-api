package br.com.vehicle.insurer.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vehicle.insurer.domain.exception.CustomerAlreadyExistsException;
import br.com.vehicle.insurer.domain.exception.EntityNotFoundException;
import br.com.vehicle.insurer.domain.model.Client;
import br.com.vehicle.insurer.domain.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		if (client.getId() != null) {
			Client alreadyExists = searchByCpf(client.getCpf());
			if (alreadyExists.equals(client)) throw new CustomerAlreadyExistsException(
					String.format("Cliente com CPF '%s' já está cadastrado", client.getCpf()));
		}
		
		return clientRepository.save(client);
	}
	
	public void delete(String id) {
		Client client = searchById(id);
		clientRepository.delete(client);
	}
	
	public Client searchById(String id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Cliente de ID '%s' não encontrado", id)));
	}
	
	public Client searchByCpf(String cpf) {
		return clientRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Cliente de CPF '%s' não encontrado", cpf)));
	}
	
	public List<Client> list() {
		return clientRepository.findAll();
	}
	
}