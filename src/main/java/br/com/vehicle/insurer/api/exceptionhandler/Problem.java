package br.com.vehicle.insurer.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Problem {
	
	private final LocalDateTime timestamp;
	
	private final String error;
	
	private final List<String> errors;
	
}