package br.edu.unicatolica.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.edu.unicatolica.dao.GrupoUsuarioDAO;
import br.edu.unicatolica.exception.RegistroExistenteException;
import br.edu.unicatolica.model.GrupoUsuario;

public class GrupoUsuarioService implements Serializable {

	private static final long serialVersionUID = 747137309236170853L;

	@Inject
	private GrupoUsuarioDAO grupoUsuarioDAO;

	@Transactional
	public GrupoUsuario save(GrupoUsuario grupo) {
		GrupoUsuario existente = grupoUsuarioDAO.findByNome(grupo.getNome());

		if (existente != null && !existente.equals(grupo))
			throw new RegistroExistenteException("Grupo de usuários já existente.");

		return grupoUsuarioDAO.save(grupo);
	}

	@Transactional
	public void mudarStatus(GrupoUsuario grupo) {
		grupo.mudarStatus();
		grupoUsuarioDAO.save(grupo);
	}

	public List<GrupoUsuario> findAll() {
		return grupoUsuarioDAO.findAll();
	}

	public GrupoUsuario findOneComPermissoes(Integer id) {
		return grupoUsuarioDAO.findOneComPermissoes(id);
	}

	public List<GrupoUsuario> findAllComPermissoes() {
		return grupoUsuarioDAO.findAllComPermissoes();
	}

	public List<GrupoUsuario> pesquisar() {
		return grupoUsuarioDAO.findAll();
	}

}