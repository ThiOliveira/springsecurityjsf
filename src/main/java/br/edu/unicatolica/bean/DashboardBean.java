package br.edu.unicatolica.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unicatolica.model.Usuario;
import br.edu.unicatolica.security.Logado;

@Named
@ViewScoped
public class DashboardBean implements Serializable {

	private static final long serialVersionUID = -74357325170790897L;

	@Inject @Logado
	private Usuario usuarioLogado;

	private String matricula;

	@PostConstruct
	public void init() {
		matricula = usuarioLogado.getMatricula();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}