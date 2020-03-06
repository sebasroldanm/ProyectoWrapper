package edu.udec.excepcion;

/*
 * Clase modelo para los errores
 */
public class NotFoundModelException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundModelException(String mensaje) {		
		super(mensaje);
	}

}
