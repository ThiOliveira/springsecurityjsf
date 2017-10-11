package br.edu.unicatolica.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.infra.cdi.CDIServiceLocator;
import br.edu.unicatolica.model.Usuario;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		Usuario usuario = CDIServiceLocator.getBean(UsuarioDAO.class).findByMatricula(matricula);
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		UsuarioDAO usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		List<String> permissoes = usuarioDAO.permissoes(usuario);
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority("ROLE_" + p.toUpperCase())));

		return authorities;
	}
}