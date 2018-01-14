package br.com.sistema.mcosta.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.Cliente;
import br.com.sistema.mcosta.entidade.ClienteServico;
import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.repositorio.IClienteRepositorio;
import br.com.sistema.mcosta.repositorio.IClienteServicoRepositorio;

@Service
public class ClienteBO {

	@Autowired
	private IClienteRepositorio clienteRepositorio;

	@Autowired
	private IClienteServicoRepositorio clienteServicoRepositorio;

	@Autowired
	private ServicoBO servicoBO;

	public Cliente salvar(Cliente cliente) {

		List<Long> servicos = cliente.getServicosIds();
		cliente = clienteRepositorio.save(cliente);

		if (servicos != null) {
			clienteServicoRepositorio.deleteByClienteId(cliente.getId());
			for (Long item : servicos) {
				clienteServicoRepositorio.save(new ClienteServico(cliente.getId(), item));
			}
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
		cliente.setServicosIds(new ArrayList<>());
		buscarServicosCliente(id).forEach(item -> cliente.getServicosIds().add(item.getServico().getId()));
		return cliente;
	}

	@Transactional(readOnly = true)
	public List<ClienteServico> buscaClienteEServicoPorId(Long id) {
		Cliente cliente = clienteRepositorio.findOne(id);
		List<Servico> servicos = servicoBO.buscarTodosReduzido();
		List<ClienteServico> list = new ArrayList<>();
		buscarServicosCliente(id).forEach(
				item -> list.add(new ClienteServico(cliente, servicos.stream().filter(x -> x.getId().equals(item.getId())).findAny().get())));
		return list;
	}

	@Transactional(readOnly = true)
	public List<ClienteServico> buscarServicosCliente(Long id) {
		return clienteServicoRepositorio.findByClienteId(id);
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