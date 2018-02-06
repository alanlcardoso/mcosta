package br.com.sistema.mcosta.entidade;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import br.com.sistema.mcosta.util.Util;

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
	
	@OneToMany(mappedBy="cliente")
	private List<ClienteServico> servicos;

	@Transient
	private List<Long> servicosIds = new ArrayList<>();
	
	public Cliente() {
	}

	public Cliente(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Cliente(Long id) {
		this.id = id;
	}

	public Byte[] getLogo() {
		return logo;
	}

	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}

	public String getLogoBase64() {
		return Base64.getEncoder().encodeToString(Util.toPrimitives(this.logo));
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

	public List<Long> getServicosIds() {
		return servicosIds;
	}

	public void setServicosIds(List<Long> servicosIds) {
		this.servicosIds = servicosIds;
	}
}
