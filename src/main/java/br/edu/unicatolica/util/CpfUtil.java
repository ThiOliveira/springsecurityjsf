package br.edu.unicatolica.util;

import java.io.Serializable;

public class CpfUtil implements Serializable {

	private static final long serialVersionUID = 7918603919491582559L;

	public String somenteNumeros(String cpf) {
		return cpf.replaceAll("[.-]", "");
	}

	public String comFormatacao(String cpf) {
		String cpfFormatado = cpf.substring(0, 3) + ".";
		cpfFormatado += cpf.substring(3, 6) + ".";
		cpfFormatado += cpf.substring(6, 9) + "-";
		cpfFormatado += cpf.substring(9, 11);
		return cpfFormatado;
	}

}