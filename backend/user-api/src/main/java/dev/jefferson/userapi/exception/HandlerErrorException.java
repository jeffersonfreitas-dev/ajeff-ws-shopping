package dev.jefferson.userapi.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerErrorException {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ApiError handlerResourceNotFoundException(ResourceNotFoundException ex) {
		return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), new Date());
	}

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = MissingServletRequestParameterException.class)
	public ApiError handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		String error = "O parâmetro " + ex.getParameterName() + " deve ser informado!";
		return new ApiError(HttpStatus.BAD_REQUEST, error, new Date());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( MethodArgumentNotValidException.class)
	public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		String message = "Ocorreu um erro de validação";
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " : " + error.getDefaultMessage());
		}
		
		for(ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
		}
		
		return new ApiError(HttpStatus.BAD_REQUEST, message, new Date(), errors);
	}
}
