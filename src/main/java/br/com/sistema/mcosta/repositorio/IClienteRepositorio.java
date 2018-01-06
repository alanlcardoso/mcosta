package br.com.sistema.mcosta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.mcosta.entidade.Cliente;

public interface IClienteRepositorio extends JpaRepository<Cliente, Long> {

	@Query("SELECT new br.com.sistema.mcosta.entidade.Cliente(cli.id, cli.nome) FROM Cliente cli")
	List<Cliente> buscarTodosReduzido();
	
}