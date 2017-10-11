package br.edu.unicatolica.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unicatolica.exception.RegistroExistenteException;
import br.edu.unicatolica.infra.jsf.FacesUtil;
import br.edu.unicatolica.infra.jsf.MessagesHelper;
import br.edu.unicatolica.model.GrupoUsuario;
import br.edu.unicatolica.model.Usuario;
import br.edu.unicatolica.service.GrupoUsuarioService;
import br.edu.unicatolica.service.UsuarioService;

@Named
@ViewScoped
public class CadastrarUsuarioBean implements Serializable {

	private static final long serialVersionUID = 4089832954506659515L;

	@Inject
	private UsuarioService service;

	@Inject
	private GrupoUsuarioService grupoUsuarioService;

	@Inject
	private MessagesHelper helper;

	@Inject
	private FacesUtil facesUtil;

	private List<GrupoUsuario> grupos;

	private Usuario usuario = new Usuario();

	@PostConstruct
	public void init() {
		String matricula = facesUtil.getParamMatricula("id");

		if (matricula != null)
			usuario = service.findUsuarioComGruposByMatricula(matricula);
	}

	public String salvar() {
		try {
			service.saveOrUpdate(usuario);
			helper.addFlash("Usuário salvo com sucesso!");
			return "/private/usuarios/pesquisar-usuarios.xhtml?faces-redirect=true";
		} catch (RegistroExistenteException e) {
			helper.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao salvar o usuário!");
		}
		return null;
	}

	public String atualizar() {
		try {
			service.saveOrUpdate(usuario);
			helper.addFlash("Usuário atualizado com sucesso!");
			return "/private/usuarios/pesquisar-usuarios.xhtml?faces-redirect=true";
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao atualizar o usuário!");
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<GrupoUsuario> getGrupos() {
		if (grupos == null)
			grupos = grupoUsuarioService.findAll();

		return grupos;
	}

}