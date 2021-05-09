package br.com.vehicle.insurer.domain.exception;

import java.util.List;

import org.springframework.validation.ObjectError;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final List<ObjectError> errors;
	
	public ValidationException(List<ObjectError> errors) {
		this.errors = errors;
	}

}