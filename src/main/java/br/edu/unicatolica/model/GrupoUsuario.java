package br.edu.unicatolica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import br.edu.unicatolica.util.AES;

@NamedQueries({
		@NamedQuery(name = "GrupoUsuario.findByNome", query = "SELECT g FROM GrupoUsuario g WHERE g.nome = :nome"),
		@NamedQuery(name = "GrupoUsuario.findAll", query = "SELECT g FROM GrupoUsuario g ORDER BY g.nome") })

@Cacheable
@Entity
@Table(name = "GRUPO")
public class GrupoUsuario implements Serializable {

	private static final long serialVersionUID = 8650630354073498789L;

	private Integer id;

	private String nome;

	private List<Permissao> permissoes = new ArrayList<>();

	private boolean ativo = true;

	public GrupoUsuario() {
	}

	public GrupoUsuario(String nome) {
		this.nome = nome;
	}

	@Id
	@GeneratedValue
	@Column(name = "GRU_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank
	@Column(name = "GRU_NOME", length = 45, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToMany
	@JoinTable(name = "GRUPO_PERMISSAO", joinColumns = @JoinColumn(name = "ID_GRUPO"), inverseJoinColumns = @JoinColumn(name = "ID_PERMISSAO"))
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Column(name = "GRU_IS_ATIVO")
	@Type(type = "true_false")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Transient
	public boolean isNovo() {
		return id == null;
	}

	@Transient
	public boolean isCadastrado() {
		return !isNovo();
	}

	@Transient
	public String encodedId() {
		return new AES().codificar(id.toString());
	}

	@Transient
	public void mudarStatus() {
		this.ativo = !ativo;
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
		GrupoUsuario other = (GrupoUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
