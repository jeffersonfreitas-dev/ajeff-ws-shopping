package dev.jefferson.productapi.exception;

public class ResourceAlreadyRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyRegisteredException(String msg) {
		super(msg);
	}

}
