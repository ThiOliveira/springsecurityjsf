package br.edu.unicatolica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.QueryHints;

import br.edu.unicatolica.model.GrupoUsuario;

public class GrupoUsuarioDAO implements Serializable {

	private static final long serialVersionUID = -4550789955653382737L;

	@Inject
	private EntityManager manager;

	public GrupoUsuario save(GrupoUsuario grupo) {
		return manager.merge(grupo);
	}

	public List<GrupoUsuario> findAll() {
		return manager.createNamedQuery("GrupoUsuario.findAll", GrupoUsuario.class)
				.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
	}

	public GrupoUsuario findByNome(String nome) {
		try {
			return manager.createNamedQuery("GrupoUsuario.findByNome", GrupoUsuario.class).setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public GrupoUsuario findOneComPermissoes(Integer id) {
		try {
			return manager.createQuery("SELECT g FROM GrupoUsuario g LEFT JOIN FETCH g.permissoes p WHERE g.id = :id",
					GrupoUsuario.class).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<GrupoUsuario> findAllComPermissoes() {
		try {
			return manager
					.createQuery("SELECT g FROM GrupoUsuario g LEFT JOIN FETCH g.permissoes p", GrupoUsuario.class)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}