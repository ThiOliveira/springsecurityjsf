package br.edu.unicatolica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unicatolica.dao.PermissaoDAO;
import br.edu.unicatolica.exception.RegistroExistenteException;
import br.edu.unicatolica.infra.jsf.FacesUtil;
import br.edu.unicatolica.infra.jsf.MessagesHelper;
import br.edu.unicatolica.model.Permissao;

@Named
@ViewScoped
public class PermissaoBean implements Serializable {

	private static final long serialVersionUID = 6958517772569167273L;

	@Inject
	private PermissaoDAO permissaoDAO;

	@Inject
	private MessagesHelper helper;

	private List<Permissao> permissoes;

	private Permissao permissao = new Permissao();

	public void salvar() {
		try {
			nomePermissaoToUpperCase();
			permissaoDAO.save(permissao);
			onSuccess("Permissão salva com sucesso!");
		} catch (RegistroExistenteException e) {
			helper.validationFailed(e.getMessage());
		} catch (Exception e) {
			onError("Erro ao tentar salvar a permissão!");
			e.printStackTrace();
		}
	}

	public void atualizar() {
		try {
			nomePermissaoToUpperCase();
			permissaoDAO.save(permissao);
			onSuccess("Permissão atualizada com sucesso!");
		} catch (Exception e) {
			onError("Erro ao tentar atualizar a permissão!");
			e.printStackTrace();
		}
	}

	private void onSuccess(String message) {
		helper.addInfoMessage(message);
		FacesUtil.updateComponents("frm:msg", "frm:permissoes-table");
		permissoes = null;
	}

	private void onError(String message) {
		helper.addErrorMessage(message);
	}

	public void clear() {
		this.permissao = new Permissao();
	}

	private void nomePermissaoToUpperCase() {
		this.permissao.setNome(this.permissao.getNome().toUpperCase().trim());
	}

	public Permissao getpermissao() {
		return permissao;
	}

	public void setpermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<Permissao> getPermissoes() {
		if (permissoes == null)
			permissoes = permissaoDAO.findAll();

		return permissoes;
	}
}