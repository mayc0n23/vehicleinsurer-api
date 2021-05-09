package br.com.vehicle.insurer.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.vehicle.insurer.domain.model.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

	Optional<Client> findByCpf(String cpf);
	
	boolean existsByCpf(String cpf);
	
}