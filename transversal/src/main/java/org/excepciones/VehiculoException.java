package org.excepciones;

public class VehiculoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public VehiculoException(String mensaje) {
		super(mensaje);
	}
	public VehiculoException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
