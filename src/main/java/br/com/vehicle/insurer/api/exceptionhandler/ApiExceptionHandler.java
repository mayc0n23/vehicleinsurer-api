package br.com.vehicle.insurer.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vehicle.insurer.domain.exception.CustomerAlreadyExistsException;
import br.com.vehicle.insurer.domain.exception.EntityNotFoundException;
import br.com.vehicle.insurer.domain.exception.ValidationException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro de validação";
		List<String> errors = ex.getErrors().stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
		Problem problem = Problem.builder()
				.timestamp(LocalDateTime.now())
				.error(error)
				.errors(errors)
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<Object> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = ex.getMessage();
		Problem problem = Problem.builder()
				.timestamp(LocalDateTime.now())
				.error(error)
				.errors(Collections.emptyList())
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = ex.getMessage();
		Problem problem = Problem.builder()
				.timestamp(LocalDateTime.now())
				.error(error)
				.errors(Collections.emptyList())
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.error(status.getReasonPhrase())
					.errors(Collections.emptyList())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.error((String) body)
					.errors(Collections.emptyList())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
}