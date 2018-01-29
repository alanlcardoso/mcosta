package br.com.sistema.mcosta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.mcosta.entidade.ItemServico;

public interface IItemServicoRepositorio extends JpaRepository<ItemServico, Long> {
	
	@Query("FROM ItemServico WHERE servico.id = ?")
	List<ItemServico> buscarPorIdServico(Long id);
	
}