package br.com.sistema.mcosta.entidade;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

public class Cliente {

	@Id
	@GenericGenerator(name = "generator_cliente", strategy = "increment")
	@GeneratedValue(generator = "generator_cliente")
	private Long id;

	@NotBlank(message = "Nome é obrigatório.")
	private String nome;

	@OneToMany(mappedBy = "cliente")
	private List<Servico> servicos;

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

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}
