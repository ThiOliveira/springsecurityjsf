package br.edu.unicatolica.exception;

import java.io.Serializable;

public class NegocioException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -2271953948484505047L;

	public NegocioException(String msg) {
		super(msg);
	}
	
}