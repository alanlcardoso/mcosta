package br.com.sistema.mcosta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.mcosta.entidade.Servico;

public interface IServicoRepositorio extends JpaRepository<Servico, Long> {

	@Query("SELECT new br.com.sistema.mcosta.entidade.Servico(cli.id, cli.nome, cli.descricao) FROM Servico cli")
	List<Servico> buscarTodosReduzido();
}