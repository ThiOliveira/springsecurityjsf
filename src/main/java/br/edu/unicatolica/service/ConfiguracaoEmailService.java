package br.edu.unicatolica.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.edu.unicatolica.dao.ConfiguracaoEmailDAO;
import br.edu.unicatolica.email.ConfiguracaoEmail;

public class ConfiguracaoEmailService implements Serializable {

	private static final long serialVersionUID = 504045890216191078L;

	@Inject
	private ConfiguracaoEmailDAO dao;

	public ConfiguracaoEmail getConfiguracao() {
		return dao.findConfiguracao();
	}

	@Transactional
	public void saveOrUpdate(ConfiguracaoEmail email) {
		dao.save(email);
	}
}