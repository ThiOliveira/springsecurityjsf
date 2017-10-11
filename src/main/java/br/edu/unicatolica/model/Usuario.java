package br.edu.unicatolica.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.edu.unicatolica.util.AES;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1444080045258883005L;

	private Long id;

	private String matricula;

	private String nome;

	private String cpf;

	private String email;

	private String senha;

	private List<GrupoUsuario> grupos;

	private boolean ativo = true;

	private boolean mudarSenha = true;

	public Usuario() {

	}

	public Usuario(String matricula) {
		this.matricula = matricula;
	}

	@Id
	@GeneratedValue
	@Column(name = "USU_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Column(name = "USU_MATRICULA", nullable = false, length = 15, unique = true)
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@NotBlank
	@Column(name = "USU_NOME", nullable = false, length = 100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotBlank
	@CPF(message = "CPF inválido!")
	@Column(name = "USU_CPF", nullable = false, length = 20)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotBlank
	@Email(message = "E-mail inválido!")
	@Column(name = "USU_EMAIL", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@Column(name = "USU_SENHA", nullable = false, length = 64)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Size(min = 1, message = "Selecione pelo menos um grupo!")
	@ManyToMany
	@JoinTable(name = "USUARIO_GRUPO", joinColumns = @JoinColumn(name = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_GRUPO"))
	public List<GrupoUsuario> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoUsuario> grupos) {
		this.grupos = grupos;
	}

	@Column(name = "USU_IS_ATIVO")
	@Type(type = "true_false")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Column(name = "USU_IS_MUDAR_SENHA")
	@Type(type = "true_false")
	public boolean isMudarSenha() {
		return mudarSenha;
	}

	public void setMudarSenha(boolean mudarSenha) {
		this.mudarSenha = mudarSenha;
	}

	@Transient
	public void mudarStatus() {
		this.ativo = !ativo;
	}

	@Transient
	public String encodedId() {
		return new AES().codificar(matricula);
	}

	@Transient
	public boolean isNovo() {
		return id == null;
	}

	@Transient
	public boolean isCadastrado() {
		return !isNovo();
	}

	@PostLoad
	public void posLoad() {
		this.cpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}