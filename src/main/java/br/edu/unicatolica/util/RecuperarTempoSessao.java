package br.edu.unicatolica.util;

import java.time.Duration;

import javax.servlet.http.HttpSession;

public class RecuperarTempoSessao {
	
	public String getTempoRestante(HttpSession session) {
        long tempoPassado = System.currentTimeMillis() - session.getLastAccessedTime();
        long timeOut = (session.getMaxInactiveInterval() * 1000) - tempoPassado;
        
        Duration duracao = Duration.ofMillis(timeOut);
        long s = duracao.getSeconds();
        
        return String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
    }

}