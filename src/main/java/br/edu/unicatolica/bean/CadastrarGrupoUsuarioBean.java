package br.edu.unicatolica.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.edu.unicatolica.dao.PermissaoDAO;
import br.edu.unicatolica.exception.RegistroExistenteException;
import br.edu.unicatolica.infra.jsf.FacesUtil;
import br.edu.unicatolica.infra.jsf.MessagesHelper;
import br.edu.unicatolica.model.GrupoUsuario;
import br.edu.unicatolica.model.Permissao;
import br.edu.unicatolica.service.GrupoUsuarioService;

@Named
@ViewScoped
public class CadastrarGrupoUsuarioBean implements Serializable {

	private static final long serialVersionUID = -3910562075902107128L;

	@Inject
	private GrupoUsuarioService service;

	@Inject
	private PermissaoDAO permissaoDAO;

	@Inject
	private MessagesHelper helper;

	@Inject
	private FacesUtil facesUtil;

	private List<Permissao> acessoNegado;

	private List<Permissao> acessoPermitido;

	private DualListModel<Permissao> permissoes;

	private GrupoUsuario grupo = new GrupoUsuario();

	@PostConstruct
	public void init() {
		String id = facesUtil.getParamMatricula("id");

		if (id != null)
			grupo = service.findOneComPermissoes(Integer.parseInt(id));

		this.acessoNegado = permissaoDAO.findAll();
		this.acessoPermitido = grupo.getPermissoes();
		this.acessoNegado.removeAll(acessoPermitido);

		this.permissoes = new DualListModel<>(acessoNegado, acessoPermitido);
	}

	public String salvar() {
		try {
			grupo.setPermissoes(permissoes.getTarget());
			nomeGrupoToUpperCase();
			service.save(grupo);
			helper.addFlash("Grupo de usuários salvo com sucesso!");
			return "/private/usuarios/pesquisar-grupos-usuarios.xhtml?faces-redirect=true";
		} catch (RegistroExistenteException e) {
			helper.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao salvar o usuário!");
		}
		return null;
	}

	public String atualizar() {
		try {
			grupo.setPermissoes(permissoes.getTarget());
			nomeGrupoToUpperCase();
			service.save(grupo);
			helper.addFlash("Grupo de usuários atualizado com sucesso!");
			return "/private/usuarios/pesquisar-grupos-usuarios.xhtml?faces-redirect=true";
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao atualizar o usuário!");
		}
		return null;
	}

	private void nomeGrupoToUpperCase() {
		this.grupo.setNome(this.grupo.getNome().toUpperCase().trim());
	}

	public GrupoUsuario getGrupo() {
		return grupo;
	}

	public DualListModel<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(DualListModel<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}