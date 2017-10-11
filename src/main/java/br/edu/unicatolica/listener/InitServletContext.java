package br.edu.unicatolica.listener;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.edu.unicatolica.dao.PermissaoDAO;
import br.edu.unicatolica.infra.cdi.CDIServiceLocator;
import br.edu.unicatolica.model.GrupoUsuario;
import br.edu.unicatolica.model.Permissao;
import br.edu.unicatolica.model.Usuario;
import br.edu.unicatolica.service.GrupoUsuarioService;
import br.edu.unicatolica.service.UsuarioService;

@WebListener
public class InitServletContext implements ServletContextListener {
	private ServletContext context;
//	private UsuarioService usuarioService = CDIServiceLocator.getBean(UsuarioService.class);
//	private GrupoUsuarioService grupoUsuarioService = CDIServiceLocator.getBean(GrupoUsuarioService.class);
//	private PermissaoDAO permissaoDAO = CDIServiceLocator.getBean(PermissaoDAO.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();

//		executarAoIniciarServidor();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

//	private void executarAoIniciarServidor() {
//		criaUsuario(buscaUsuarioPadrao());
//	}
//
//	private Usuario buscaUsuarioPadrao() {
//		context.log("Buscando usuário com perfil de ADMINISTRADOR...");
//		return usuarioService.findByMatricula("uncqadmin");
//	}
//
//	private void criaUsuario(Usuario usuario) {
//		if (usuario == null) {
//			context.log("Criando usuário padrão...");
//			usuario = new Usuario();
//			usuario.setNome("Administrador");
//			usuario.setCpf("448.484.101-00");
//			usuario.setEmail("sistemas_lapis@unicatolicaquixada.edu.br");
//			usuario.setMatricula("uncqadmin");
//			usuario.setGrupos(Arrays.asList(criarGrupoAdministradores()));
//
//			context.log("Usuário \"uncqadmin\" criado com sucesso!");
//
//			usuarioService.saveOrUpdate(usuario);
//		} else {
//			context.log("Usuário padrão encontrado! OK...");
//		}
//	}
//
//	private GrupoUsuario criarGrupoAdministradores() {
//		context.log("Criando grupo de usuários ADMINISTRADORES...");
//		GrupoUsuario grupo = new GrupoUsuario("ADMINISTRADORES");
//		grupo.setPermissoes(criarPermissoes());
//		grupo = grupoUsuarioService.save(grupo);
//		return grupo;
//	}
//
//	private List<Permissao> criarPermissoes() {
//		context.log("Criando permissões para o grupo de usuários ADMINISTRADORES...");
//		Permissao cadastrarUsuario = permissaoDAO
//				.save(new Permissao("CADASTRAR_USUARIO", "Cadastrar usuários no sistema"));
//		Permissao listarUsuarios = permissaoDAO
//				.save(new Permissao("LISTAR_USUARIOS", "Listar usuários cadastrados no sistema"));
//		Permissao cadastrarGrupoUsuario = permissaoDAO
//				.save(new Permissao("CADASTRAR_GRUPO_USUARIO", "Cadastrar grupos de usuários no sistema"));
//		Permissao listarGruposUsuarios = permissaoDAO
//				.save(new Permissao("LISTAR_GRUPOS_USUARIOS", "Listar grupos de usuários cadastrados no sistema"));
//		Permissao manterConfiguracaoEmail = permissaoDAO
//				.save(new Permissao("MANTER_CONFIGURACAO_EMAIL", "Manter configuração do e-mail do sistema"));
//		Permissao manterPermissoes = permissaoDAO
//				.save(new Permissao("MANTER_PERMISSOES", "Manter permissões do sistema"));
//		return Arrays.asList(cadastrarUsuario, listarUsuarios, cadastrarGrupoUsuario, listarGruposUsuarios,
//				manterConfiguracaoEmail, manterPermissoes);
//	}
}