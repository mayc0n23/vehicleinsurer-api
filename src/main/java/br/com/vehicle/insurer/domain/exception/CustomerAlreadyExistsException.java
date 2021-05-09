package br.com.vehicle.insurer.domain.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
	}

}