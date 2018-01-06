package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.Cliente;
import br.com.sistema.mcosta.entidade.ClienteServico;
import br.com.sistema.mcosta.repositorio.IClienteRepositorio;
import br.com.sistema.mcosta.repositorio.IClienteServicoRepositorio;

@Service
public class ClienteBO {

	@Autowired
	private IClienteRepositorio clienteRepositorio;

	@Autowired
	private IClienteServicoRepositorio clienteServicoRepositorio;

	@Transactional(propagation = Propagation.REQUIRED)
	public Cliente salvar(Cliente cliente) {

		List<ClienteServico> servicos = cliente.getServicos();
		cliente = clienteRepositorio.save(cliente);

		clienteServicoRepositorio.deleteByClienteId(cliente.getId());
		servicos.forEach(clienteServicoRepositorio::save);

		return cliente;
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return clienteRepositorio.findAll();
	}

	@Transactional(readOnly = true)
	public List<ClienteServico> buscarServicoCliente(Long clienteId) {
		return clienteServicoRepositorio.findByClienteId(clienteId);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodosReduzido() {
		return clienteRepositorio.buscarTodosReduzido();
	}

	@Transactional(readOnly = true)
	public Cliente buscaPorId(Long id) {
		Cliente cliente = clienteRepositorio.findOne(id);
		cliente.setServicos(clienteServicoRepositorio.findByClienteId(id));
		return cliente;
	}
	
	@Transactional(readOnly = true)
	public long buscarTotalCliente() {
		return clienteRepositorio.count();
	}
}