package br.edu.unicatolica.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaUtil implements Serializable {

	private static final long serialVersionUID = -3162706690500945915L;

	/**
	 * Gera uma senha baseado em números, letras minúsculas e maiúsculas e
	 * alguns símbolos
	 * 
	 * @return
	 */
	public String geradorDeSenha() {

		String[] carct = {

		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",

		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
				"Y", "Z",

				"@", "#", "$", "%", "&"

		};

		String senha = "";

		for (int x = 0; x < 10; x++) {
			int j = (int) (Math.random() * carct.length);
			senha += carct[j];
		}

		return senha;
	}

	/**
	 * Cria um hash baseado na String 'senha' passada por parâmetro
	 * 
	 * @param senha
	 * @return
	 */
	public String toMD5(String senha) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(senha.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}