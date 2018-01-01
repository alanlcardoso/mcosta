package br.com.sistema.mcosta.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "sobre")
public class Sobre implements Serializable {

	private static final long serialVersionUID = 8684062315905211824L;
	
	@Id
	@GenericGenerator(name = "generator_sobre", strategy = "increment")
	@GeneratedValue(generator = "generator_sobre")
	private Long id;

	@NotBlank(message = "Nome é obrigatório.")
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória.")
	private String descricao;
	
	@NotBlank(message = "Ícone é obrigatório.")
	private String icone;
	
	public Sobre() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIcone() {
		return icone;
	}
	
	public void setIcone(String icone) {
		this.icone = icone;
	}
	
}
