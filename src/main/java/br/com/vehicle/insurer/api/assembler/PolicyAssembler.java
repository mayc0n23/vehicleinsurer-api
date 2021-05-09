package br.com.vehicle.insurer.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vehicle.insurer.api.dto.PolicyDTO;
import br.com.vehicle.insurer.domain.model.Policy;

@Component
public class PolicyAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PolicyDTO toDTO(Policy policy) {
		return modelMapper.map(policy, PolicyDTO.class);
	}
	
	public List<PolicyDTO> toCollectionDTO(List<Policy> policies) {
		return policies.stream()
				.map(policy -> toDTO(policy))
				.collect(Collectors.toList());
	}
	
}