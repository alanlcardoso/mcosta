package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.repositorio.IServicoRepositorio;

@Service
public class ServicoBO {

	@Autowired
	private IServicoRepositorio servicoRepositorio;

	@CacheEvict(value = "paginaInicial", allEntries = true)
	public void salvar(Servico servico) {
		servicoRepositorio.save(servico);
	}

	public void excluir(Long id) {
		excluir(new Servico(id));
	}

	public void excluir(Servico servico) {
		servicoRepositorio.delete(servico);
	}

	@Transactional(readOnly = true)
	@Cacheable("paginaInicial")
	public List<Servico> buscarTodos() {
		return servicoRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Servico> buscarTodosReduzido() {
		return servicoRepositorio.buscarTodosReduzido();
	}

	@Transactional(readOnly = true)
	public Servico buscarPorId(Long id) {
		return servicoRepositorio.findOne(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Servico> buscarClientesPorPagina(int quantidade) {
		return servicoRepositorio.buscarTodosPaginaPrincipal(new PageRequest(0, quantidade));
	}
}
