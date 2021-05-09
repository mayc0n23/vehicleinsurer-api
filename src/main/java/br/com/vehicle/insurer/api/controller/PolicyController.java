package br.com.vehicle.insurer.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vehicle.insurer.api.assembler.PolicyAssembler;
import br.com.vehicle.insurer.api.disassembler.PolicyDisassembler;
import br.com.vehicle.insurer.api.dto.PolicyDTO;
import br.com.vehicle.insurer.domain.exception.ValidationException;
import br.com.vehicle.insurer.domain.model.Policy;
import br.com.vehicle.insurer.domain.service.PolicyService;

@RestController
@RequestMapping("/policies")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private PolicyAssembler policyAssembler;
	
	@Autowired
	private PolicyDisassembler policyDisassembler;
	
	@GetMapping
	public List<PolicyDTO> list() {
		return policyAssembler.toCollectionDTO(policyService.list());
	}
	
	@GetMapping("/id/{id}")
	public PolicyDTO searchById(@PathVariable String id) {
		return policyAssembler.toDTO(policyService.search(id));
	}
	
	@GetMapping("/policyNumber/{policyNumber}")
	public PolicyDTO searchByPolicyNumber(@PathVariable int policyNumber) {
		return policyAssembler.toDTO(policyService.search(policyNumber));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PolicyDTO save(@RequestBody @Valid PolicyDTO policyDTO, Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors.getAllErrors());
		}
		
		PolicyDTO response = policyAssembler.toDTO(
				policyService.save(policyDisassembler.toDomainObject(policyDTO)));
		return response;
	}
	
	@PutMapping("/{id}")
	public PolicyDTO update(@PathVariable String id, @RequestBody @Valid PolicyDTO policyDTO, Errors errors) {
		Policy actual = policyService.search(id);
		policyDisassembler.copyToDomainObject(policyDTO, actual);
		return policyAssembler.toDTO(policyService.save(actual));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		policyService.delete(id);
	}
	
}