package dev.jefferson.shoppingapi.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.jefferson.shoppingapi.service.ResourceNotAvailableEException;

@RestControllerAdvice
public class HandlerErrorException {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ApiError handlerResourceNotFoundException(ResourceNotFoundException ex) {
		return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), new Date());
	}

	
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	@ExceptionHandler(ResourceNotAvailableEException.class)
	public ApiError handlerResourceNotAvailableException(ResourceNotAvailableEException ex) {
		return new ApiError(HttpStatus.REQUEST_TIMEOUT, ex.getMessage(), new Date());
	}

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiError handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		String msg = "Erro ao converter o UUID. Verifique se o valor informado está correto";
		return new ApiError(HttpStatus.BAD_REQUEST, msg, new Date());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ResourceAlreadyRegisteredException.class)
	public ApiError handlerResourceAlreadyRegisteredException(ResourceAlreadyRegisteredException ex) {
		return new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), new Date());
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

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ApiError handleConstraintViolationExceptionException(ConstraintViolationException ex) {
		return new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), new Date());
	}
}
