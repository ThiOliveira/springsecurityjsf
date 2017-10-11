package br.edu.unicatolica.email;

import java.io.File;
import java.util.List;

public class EnviadorDeEmail {

	private ModuloEmail modulo;

	public EnviadorDeEmail() {
		modulo = new ModuloEmail();
	}

	public void enviar(Email email) {
		if (email.semDestinatario())
			throw new IllegalStateException("Selecione pelo menos um destinatário.");

		modulo.enviarEmail(email.getAssunto(), EmailTemplate.mensagem(email.getMensagem()), "Unicatólica SGO",
				email.getEmails(), true);
	}

	public void mensagem(String assunto, String mensagem, File arquivo, List<String> destinatarios) {
		modulo.enviarEmail(assunto, EmailTemplate.mensagem(mensagem), arquivo, "Unicatólica SGO", destinatarios);
	}

}