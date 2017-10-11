package br.edu.unicatolica.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES {

	private final SecretKeySpec SECRET_KEY; 
	
	public AES() {
		SECRET_KEY = getKey("asdSDFGw45hjjssHJUJ6875SHs45sSDHahtksvvs34");
	}
	
	public String codificar(String value) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);

			String encoded = Base64.encodeBase64String(cipher.doFinal(value.getBytes("UTF-8")));
			
			return encoded.replace("/", "@").replace("=", "");
		} catch (Exception e) {
			throw new RuntimeException("Erro ao codificar o valor passado");
		}
	
	}
	
	public String decodificar(String value) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

			cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
			
			String decrypted = new String(cipher.doFinal(Base64
					.decodeBase64(value.replace("@", "/").concat("=="))));
					
			return decrypted;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao decodificar o valor passado");
		}
	}

	private SecretKeySpec getKey(String myKey) {

		byte[] key = myKey.getBytes();
		SecretKeySpec secretKey = null;

		MessageDigest sha = null;

		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");

			return secretKey;

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

	}

}