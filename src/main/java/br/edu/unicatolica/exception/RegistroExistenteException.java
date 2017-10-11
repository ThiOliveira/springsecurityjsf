package br.edu.unicatolica.exception;

import java.io.Serializable;

public class RegistroExistenteException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 3654904838733798019L;

	public RegistroExistenteException(String message) {
		super(message);
	}
	
}