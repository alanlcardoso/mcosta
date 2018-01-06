package br.com.sistema.mcosta.entidade;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class ClienteServico {

	@Id
	@GenericGenerator(name = "generator_cliente", strategy = "increment")
	@GeneratedValue(generator = "generator_cliente")
	private Long id;

	private Servico cliente;

	private Servico servico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Servico getCliente() {
		return cliente;
	}

	public void setCliente(Servico cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}
