package br.edu.unicatolica.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.edu.unicatolica.email.ConfiguracaoEmail;
import br.edu.unicatolica.infra.jsf.MessagesHelper;
import br.edu.unicatolica.service.ConfiguracaoEmailService;
import br.edu.unicatolica.util.AES;

@Named
@ViewScoped
public class ConfiguracaoEmailBean implements Serializable {

	private static final long serialVersionUID = 131489939315118542L;

	@Inject
	private ConfiguracaoEmailService service;

	@Inject
	private MessagesHelper helper;

	private ConfiguracaoEmail configuracao;
	private Boolean mudarSenha = false;
	private String senha;

	@PostConstruct
	public void init() {
		configuracao = service.getConfiguracao();

		if (configuracao == null) {
			configuracao = new ConfiguracaoEmail();
		}

	}

	public void save() {
		try {
			if (StringUtils.isNotBlank(senha)) {
				configuracao.setPassword(new AES().codificar(senha));
			}

			service.saveOrUpdate(configuracao);
			helper.addInfoMessage("Configuração salva com sucesso!");

		} catch (Exception e) {
			helper.addErrorMessage("Erro ao salvar a configuração!");
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			if (StringUtils.isNotBlank(senha)) {
				configuracao.setPassword(new AES().codificar(senha));
			}

			service.saveOrUpdate(configuracao);
			helper.addInfoMessage("Configuração atualizada com sucesso!");

		} catch (Exception e) {
			helper.addErrorMessage("Erro ao atualizada a configuração!");
			e.printStackTrace();
		}
	}

	public boolean isNova() {
		return configuracao.isNova();
	}

	public boolean isCadastrada() {
		return configuracao.isCadastrada();
	}

	public ConfiguracaoEmail getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(ConfiguracaoEmail configuracao) {
		this.configuracao = configuracao;
	}

	public Boolean getMudarSenha() {
		return mudarSenha;
	}

	public void setMudarSenha(Boolean mudarSenha) {
		this.mudarSenha = mudarSenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}