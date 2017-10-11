package br.edu.unicatolica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unicatolica.infra.jpa.Transactional;
import br.edu.unicatolica.model.GrupoUsuario;
import br.edu.unicatolica.model.Permissao;

public class PermissaoDAO implements Serializable {

	private static final long serialVersionUID = -4301809751226843872L;
	
	@Inject
	private EntityManager manager;
	
	@Transactional
	public Permissao save(Permissao permissao) {
		return manager.merge(permissao);
	}
	
	public List<Permissao> findAll() {
		return manager.createQuery("SELECT p FROM Permissao p ORDER BY p.descricao", Permissao.class)
					  .getResultList();
	}
	
	public List<String> porGrupoUsuario(GrupoUsuario grupo) {
		return manager.createQuery("SELECT p.nome FROM GrupoUsuario g INNER JOIN g.permissoes p WHERE g = :grupo", String.class)
				      .setParameter("grupo", grupo)
				      .getResultList();
	}
	
}