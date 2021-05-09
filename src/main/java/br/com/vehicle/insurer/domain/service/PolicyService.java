package br.com.vehicle.insurer.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vehicle.insurer.domain.exception.EntityNotFoundException;
import br.com.vehicle.insurer.domain.model.Policy;
import br.com.vehicle.insurer.domain.repository.PolicyRepository;

@Service
public class PolicyService {

	@Autowired
	private PolicyRepository policyRepository;
	
	public Policy save(Policy policy) {
		if (policy.getId() != null) {
			return policyRepository.save(policy);
		}
		int policyNumber = 0;
		boolean policyNumberAlreadyExists = false;
		
		do {
			policyNumber = (int) (100000 + Math.random() * (999999 - 100000));
			policyNumberAlreadyExists = policyRepository.existsByPolicyNumber(policyNumber);
		} while (policyNumberAlreadyExists);
		
		policy.setPolicyNumber(policyNumber);
		
		return policyRepository.save(policy);
	}
	
	public void delete(String id) {
		Policy policy = search(id);
		policyRepository.delete(policy);
	}
	
	public Policy search(String id) {
		return policyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Apólice de ID '%s' não encontrada", id)));
	}
	
	public Policy search(int policyNumber) {
		return policyRepository.findByPolicyNumber(policyNumber)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Apólice de número '%d' não encontrada", policyNumber)));
	}
	
	public List<Policy> list() {
		return policyRepository.findAll();
	}
	
}