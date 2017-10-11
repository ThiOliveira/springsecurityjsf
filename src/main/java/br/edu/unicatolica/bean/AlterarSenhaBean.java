package br.edu.unicatolica.bean;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unicatolica.exception.RegistroExistenteException;
import br.edu.unicatolica.infra.jsf.MessagesHelper;
import br.edu.unicatolica.model.Usuario;
import br.edu.unicatolica.security.Logado;
import br.edu.unicatolica.service.UsuarioService;
import br.edu.unicatolica.util.Criptografia;

@Named
@ViewScoped
public class AlterarSenhaBean implements Serializable {

	private static final long serialVersionUID = -5428635768695670393L;

	@Inject
	private UsuarioService service;

	@Inject
	private MessagesHelper helper;

	@Inject
	private Criptografia criptografia;

	@Inject
	@Logado
	private Usuario usuarioLogado;

	private String senhaAtual;

	private String senhaUsuario;

	public String confirmar() {
		try {
			senhaAtual = senhaToMd5(senhaAtual);
			if (senhaAtual.equals(usuarioLogado.getSenha())) {
				usuarioLogado.setSenha(senhaToMd5(senhaUsuario));
				usuarioLogado.setMudarSenha(false);
				service.saveOrUpdate(usuarioLogado);
				helper.addFlash("Senha alterada com sucesso!");
				return "/private/dashboard.xhtml?faces-redirect=true";
			} else {
				helper.addErrorMessage("Senha atual incorreta. Tente novamente!");
				return null;
			}
		} catch (RegistroExistenteException e) {
			helper.validationFailed(e.getMessage());
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao tentar mudar senha!");
			e.printStackTrace();
		}
		return null;
	}

	private String senhaToMd5(String senha) {
		return criptografia.criptografar(senha);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
}