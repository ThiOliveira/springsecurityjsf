package br.edu.unicatolica.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import br.edu.unicatolica.model.Usuario;

public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final String URL_MUDAR_SENHA = "/private/usuarios/alterar-senha.xhtml";
	private static final String URL_DASHBOARD = "/private/dashboard.xhtml";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();

		Usuario usuario = usuarioSistema.getUsuario();
		if (usuario.isMudarSenha()) {
			getRedirectStrategy().sendRedirect(request, response, URL_MUDAR_SENHA);
		} else {
			getRedirectStrategy().sendRedirect(request, response, URL_DASHBOARD);
		}
	}

}