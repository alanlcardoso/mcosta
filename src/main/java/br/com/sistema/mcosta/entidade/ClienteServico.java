package br.com.sistema.mcosta.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cliente_servico")
public class ClienteServico {

	@Id
	@GenericGenerator(name = "generator_cliente", strategy = "increment")
	@GeneratedValue(generator = "generator_cliente")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false, unique = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_servico", nullable = false, unique = false)
	private Servico servico;

	public ClienteServico(Long clienteId, Long servicoId) {
		this.cliente = new Cliente(clienteId);
		this.servico = new Servico(servicoId);
	}
	
	public ClienteServico(Cliente cliente, Servico servico) {
		this.cliente = cliente;
		this.servico = servico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}
