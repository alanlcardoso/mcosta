package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.ClienteServico;

public interface IClienteServicoRepositorio extends JpaRepository<ClienteServico, Long> {
	
}