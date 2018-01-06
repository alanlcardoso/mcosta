package br.com.sistema.mcosta.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistema.mcosta.entidade.Cliente;

public interface IClienteRepositorio extends JpaRepository<Cliente, Long> {

	@Query("SELECT new br.com.sistema.mcosta.entidade.Cliente(cli.id, cli.nome) FROM Cliente cli")
	List<Cliente> buscarTodosReduzido();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE Cliente x WHERE x.id = :id")
	void deleteById(@Param("id") Long id);
	
}