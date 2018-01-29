package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.ItemServico;
import br.com.sistema.mcosta.repositorio.IItemServicoRepositorio;

@Service
public class ItemServicoBO {

	@Autowired
	private IItemServicoRepositorio itemServicoRepositorio;
	
	public void salvar(ItemServico itemServico) {
		itemServicoRepositorio.save(itemServico);
	}

	public void excluir(Long id) {
		itemServicoRepositorio.delete(id);
	}

	@Transactional(readOnly = true)
	public List<ItemServico> buscarTodos() {
		return itemServicoRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public ItemServico buscarPorId(Long id) {
		return itemServicoRepositorio.findOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<ItemServico> buscarPorIdServico(Long id) {
		return itemServicoRepositorio.buscarPorIdServico(id);
	}
	
}
