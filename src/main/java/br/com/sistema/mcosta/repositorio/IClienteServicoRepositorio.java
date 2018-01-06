package br.com.sistema.mcosta.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistema.mcosta.entidade.ClienteServico;

public interface IClienteServicoRepositorio extends JpaRepository<ClienteServico, Long> {

	@Query("FROM ClienteServico x WHERE x.cliente.id = :clienteId")
	List<ClienteServico> findByClienteId(@Param("clienteId") Long clienteId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE ClienteServico x WHERE x.cliente.id = :clienteId")
	void deleteByClienteId(@Param("clienteId") Long id);

}