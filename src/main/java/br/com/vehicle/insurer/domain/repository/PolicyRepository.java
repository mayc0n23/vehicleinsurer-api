package br.com.vehicle.insurer.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.vehicle.insurer.domain.model.Policy;

@Repository
public interface PolicyRepository extends MongoRepository<Policy, String> {

	Optional<Policy> findByPolicyNumber(int policyNumber);
	
	boolean existsByPolicyNumber(int policyNumber);
	
}