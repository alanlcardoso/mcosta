package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

		if (servicos != null) {
			clienteServicoRepositorio.deleteByClienteId(cliente.getId());
			servicos.forEach(clienteServicoRepositorio::save);
		}

		return cliente;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void salvarImagem(Cliente cliente) {
		clienteRepositorio.save(cliente);
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

	@Transactional(readOnly = true)
	public Page<Cliente> buscarClientesPorPagina(int quantidade) {
		return clienteRepositorio.buscarTodosPaginaPrincipal(new PageRequest(0, quantidade));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void excluir(Long id) {
		clienteServicoRepositorio.deleteByClienteId(id);
		clienteRepositorio.deleteById(id);
	}

}