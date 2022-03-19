package dev.jefferson.userapi.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {

	private HttpStatus status;
	private String message;
	private Date timestamp;
	private List<String> errors = new ArrayList<>();
	
	public ApiError(HttpStatus status, String message, Date timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	public ApiError(HttpStatus status, String message, Date timestamp, List<String> errors) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.errors = errors;
	}
	
	
	
	
	
}
