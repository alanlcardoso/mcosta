package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.Cliente;

public interface IClienteRepositorio extends JpaRepository<Cliente, Long> {
	
}