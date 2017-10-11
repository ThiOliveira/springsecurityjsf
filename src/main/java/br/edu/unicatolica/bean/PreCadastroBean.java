package br.edu.unicatolica.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PreCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String string;
	private List<String> strings;

	@PostConstruct
	public void init() {

		pesquisar();
	}

	public void pesquisar() {
		strings = Arrays.asList("Teste", "Template");
	}

	public void salvar() {
		System.out.println("Salvou pre-cadastro...");
	}

	public void atualizar() {
		System.out.println("Atualizou pre-cadastro...");
	}

	public void clear() {
		string = null;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}