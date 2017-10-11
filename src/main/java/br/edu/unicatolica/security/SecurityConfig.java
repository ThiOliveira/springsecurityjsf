package br.edu.unicatolica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String URL_DASHBOARD = "/private/dashboard.xhtml";
	
	private static final String URL_CADASTRAR_GRUPO_USUARIO = "/private/usuarios/cadastrar-grupo-usuario.xhtml";
	private static final String URL_LISTAR_GRUPOS_USUARIOS = "/private/usuarios/pesquisar-grupos-usuarios.xhtml";

	private static final String URL_CADASTRAR_USUARIO = "/private/usuarios/cadastrar-usuario.xhtml";
	private static final String URL_LISTAR_USUARIOS = "/private/usuarios/pesquisar-usuarios.xhtml";
	
	private static final String URL_MANTER_PERMISSOES = "/private/usuarios/permissoes.xhtml";

	private static final String URL_MANTER_CONFIGURACAO_EMAIL = "/private/configuracoes/configuracao-email.xhtml";

	/**
	 * URLS DE ACESSO PÚBLICO
	 */
	private static final String URL_LOGIN = "/public/login.xhtml";
	private static final String URL_LOGIN_ERROR = URL_LOGIN + "?invalid=true";
	private static final String LOGOUT = "/logout";
	private static final String URL_ACESSO_NEGADO = "/public/403.xhtml";

	private static final String[] URLS_ACESSO_PUBLICO = new String[] { "/javax.faces.resource/**", "/public/**",
			"/resources/fonts/**", "/resources/img/**" };

	/**
	 * URLS DE ACESSO AUTENTICADO
	 */
	private static final String[] URLS_ACESSO_AUTENTICADO = new String[] { URL_DASHBOARD, URL_ACESSO_NEGADO };

	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}

	@Bean
	public AppUserDetailsService userDetailsService() {
		return new AppUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
		jsfLoginEntry.setLoginFormUrl(URL_LOGIN);
		jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());

		JsfAccessDeniedHandler handler = new JsfAccessDeniedHandler();
		handler.setLoginPath(URL_ACESSO_NEGADO);
		handler.setContextRelative(true);

		http
			.csrf()
			.disable()
			.headers()
			.frameOptions()
			.sameOrigin()
			.and()

			.authorizeRequests()
				.antMatchers(URLS_ACESSO_PUBLICO).permitAll()
				.antMatchers(URL_CADASTRAR_USUARIO).hasRole("CADASTRAR_USUARIO")
				.antMatchers(URL_LISTAR_USUARIOS).hasRole("LISTAR_USUARIOS")
				.antMatchers(URL_CADASTRAR_GRUPO_USUARIO).hasRole("CADASTRAR_GRUPO_USUARIO")
				.antMatchers(URL_LISTAR_GRUPOS_USUARIOS).hasRole("LISTAR_GRUPOS_USUARIOS")
				.antMatchers(URL_MANTER_PERMISSOES).hasRole("MANTER_PERMISSOES")
				.antMatchers(URL_MANTER_CONFIGURACAO_EMAIL).hasRole("MANTER_CONFIGURACAO_EMAIL")
				.antMatchers(URLS_ACESSO_AUTENTICADO).authenticated()
				.and()

			.formLogin()
				.loginPage(URL_LOGIN).successHandler(new CustomAuthenticationHandler())
				.failureUrl(URL_LOGIN_ERROR).and()

				.logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT)).and()

				.exceptionHandling().accessDeniedPage(URL_ACESSO_NEGADO).authenticationEntryPoint(jsfLoginEntry)
				.accessDeniedHandler(handler);
	}
}