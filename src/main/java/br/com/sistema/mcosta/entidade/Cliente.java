package br.com.sistema.mcosta.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GenericGenerator(name = "generator_cliente", strategy = "increment")
	@GeneratedValue(generator = "generator_cliente")
	private Long id;

	@NotBlank(message = "Nome é obrigatório.")
	private String nome;
	
	@Column(name = "logo", nullable = true, unique = false)
	private Byte[] logo;

	@OneToMany(mappedBy = "cliente")
	private List<ClienteServico> servicos;
	
	
	public Cliente() {
	}

	public Cliente(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Byte[] getLogo() {
		return logo;
	}

	public void setLogo(Byte[] logo) {
		this.logo = logo;
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

	public List<ClienteServico> getServicos() {
		return servicos;
	}

	public void setServicos(List<ClienteServico> servicos) {
		this.servicos = servicos;
	}
}
