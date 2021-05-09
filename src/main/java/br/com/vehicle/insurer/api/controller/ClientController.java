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

import br.com.vehicle.insurer.api.assembler.ClientAssembler;
import br.com.vehicle.insurer.api.disassembler.ClientDisassembler;
import br.com.vehicle.insurer.api.dto.ClientDTO;
import br.com.vehicle.insurer.domain.service.ClientService;
import br.com.vehicle.insurer.domain.exception.ValidationException;
import br.com.vehicle.insurer.domain.model.Client;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientAssembler clientAssembler;
	
	@Autowired
	private ClientDisassembler clientDisassembler;
	
	@GetMapping
	public List<ClientDTO> list() {
		return clientAssembler.toCollectionDTO(clientService.list());
	}
	
	@GetMapping("/{id}")
	public ClientDTO search(@PathVariable String id) {
		return clientAssembler.toDTO(clientService.searchById(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDTO save(@RequestBody @Valid ClientDTO clientDTO, Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors.getAllErrors());
		}
		
		ClientDTO response = clientAssembler.toDTO(
				clientService.save(clientDisassembler.toDomainObject(clientDTO)));
		return response;
	}
	
	@PutMapping("/{id}")
	public ClientDTO update(@PathVariable String id, @RequestBody @Valid ClientDTO clientDTO, Errors errors) {
		Client actual = clientService.searchById(id);
		clientDisassembler.copyToDomainObject(clientDTO, actual);
		return clientAssembler.toDTO(clientService.save(actual));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		clientService.delete(id);
	}
	
}