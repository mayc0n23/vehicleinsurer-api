package br.com.vehicle.insurer.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vehicle.insurer.api.dto.PolicyDTO;
import br.com.vehicle.insurer.domain.model.Policy;

@Component
public class PolicyDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Policy toDomainObject(PolicyDTO policyDTO) {
		return modelMapper.map(policyDTO, Policy.class);
	}
	
	public void copyToDomainObject(PolicyDTO policyDTO, Policy policy) {
		modelMapper.map(policyDTO, policy);
	}
	
}