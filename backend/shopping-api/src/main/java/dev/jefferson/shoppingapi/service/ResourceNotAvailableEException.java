package dev.jefferson.shoppingapi.service;

public class ResourceNotAvailableEException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotAvailableEException(String msg) {
		super(msg);
	}

}
